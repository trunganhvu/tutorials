# ElasticSearch + Kibana + Docker
## Network
docker network create elastic

## Elastic 
docker run --name elasticsearch --net elastic -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" elasticsearch:8.16.1

## Kibana
docker run -d --name kibana --net elastic -p 5601:5601 -e "ELASTICSEARCH_HOSTS=http://elasticsearch:9200" kibana:8.16.1

## URL
http://localhost:5601/

## Mapping structure
Databases ~ Indices
Tables ~ Mappings (Types < 6.0 ver) 
Records ~ Documents

Ví dụ
```sh
# Indices: products
# Mappings: properties (name, price, in_stock)
# Documents: _doc {"name": "Laptop",...}
PUT /products   
{
    "settings": {
        "number_of_shards": 3,
        "number_of_replicas": 1
    },
    "mappings": {
        "properties": {
            "name": {
                "type": "text"
            },
            "price": {
                "type": "float"
            },
            "in_stock": {
                "type": "boolean"
            }
        }
    }
}
POST /products/_doc
{
  "name": "Laptop",
  "price": 1000.00,
  "in_stock": "10"
}
```

# Command
## Cluster Health
```sh
GET _cluster/health?pretty
```

```json
{
  "cluster_name": "docker-cluster",
  "status": "yellow", // primary work, replicate unassigned
  "timed_out": false,
  "number_of_nodes": 1, // Số lượng node trong cluster
  "number_of_data_nodes": 1,    // Số lượng node chịu trách nhiệm lưu trữ dữ liệu
  "active_primary_shards": 30,  // Số lượng shard chính đang hoạt động
  "active_shards": 30,          // Tổng số shard (gồm cả chính và replica) đang hoạt động
  "relocating_shards": 0,       // Số shard đang được di chuyển giữa các node
  "initializing_shards": 0,     // Số shard đang trong quá trình khởi tạo
  "unassigned_shards": 1,       // Số shard chưa được gán cho bất kỳ node nào
  "unassigned_primary_shards": 0,// Số shard chính chưa được gán
  "delayed_unassigned_shards": 0,
  "number_of_pending_tasks": 0,
  "number_of_in_flight_fetch": 0,
  "task_max_waiting_in_queue_millis": 0,    // Thời gian dài nhất trong quere
  "active_shards_percent_as_number": 96.7741935483871   //  Phần trăm số shard đang hoạt động so với tổng số shard trong cluster = 31/30
}
```

## Cluster Health within Index Level
>Đánh giá trạng thái tổng thể của từng index trong cluster, dựa trên tình trạng của các shard liên quan.
```sh
GET _cluster/health?level=indices&pretty
```

```json
{
  "cluster_name": "docker-cluster",
  "status": "yellow",
  "timed_out": false,
  "number_of_nodes": 1,
  "number_of_data_nodes": 1,
  "active_primary_shards": 30,
  "active_shards": 30,
  "relocating_shards": 0,
  "initializing_shards": 0,
  "unassigned_shards": 1,
  "unassigned_primary_shards": 0,
  "delayed_unassigned_shards": 0,
  "number_of_pending_tasks": 0,
  "number_of_in_flight_fetch": 0,
  "task_max_waiting_in_queue_millis": 0,
  "active_shards_percent_as_number": 96.7741935483871,
  "indices": {
    ".kibana_8.16.1_001": {
      "status": "green",
      "number_of_shards": 1,
      "number_of_replicas": 0,
      "active_primary_shards": 1,
      "active_shards": 1,
      "relocating_shards": 0,
      "initializing_shards": 0,
      "unassigned_shards": 0,
      "unassigned_primary_shards": 0
    },
  }
}
```

## Cluster Health within Shard Level
>Theo dõi trạng thái của từng shard (bao gồm cả shard chính và shard replica).
```sh
GET _cluster/health?level=shards&pretty
```

```json
{
  "cluster_name": "docker-cluster",
  "status": "yellow",
  "timed_out": false,
  "number_of_nodes": 1,
  "number_of_data_nodes": 1,
  "active_primary_shards": 30,
  "active_shards": 30,
  "relocating_shards": 0,
  "initializing_shards": 0,
  "unassigned_shards": 1,
  "unassigned_primary_shards": 0,
  "delayed_unassigned_shards": 0,
  "number_of_pending_tasks": 0,
  "number_of_in_flight_fetch": 0,
  "task_max_waiting_in_queue_millis": 0,
  "active_shards_percent_as_number": 96.7741935483871,
  "indices": {
    ".internal.alerts-transform.health.alerts-default-000001": {
      "status": "green",
      "number_of_shards": 1,
      "number_of_replicas": 0,
      "active_primary_shards": 1,
      "active_shards": 1,
      "relocating_shards": 0,
      "initializing_shards": 0,
      "unassigned_shards": 0,
      "unassigned_primary_shards": 0,
      "shards": {
        "0": {
          "status": "green",
          "primary_active": true,
          "active_shards": 1,
          "relocating_shards": 0,
          "initializing_shards": 0,
          "unassigned_shards": 0,
          "unassigned_primary_shards": 0
        }
      }
    }
  }
}
```

## Nodes Overview
> Liệt kê list các node trong cluster (primary + replica)

?v hiển thị cột tiêu đề
```sh
GET _cat/nodes?v
```

```json
ip         heap.percent ram.percent cpu load_1m load_5m load_15m node.role   master name
172.19.0.2           14          73   1    0.05    0.08     0.08 cdfhilmrstw *      a97166c104cd
```



## Master Node
> Liệt kê list các master trong cluster (primary)
```sh
GET _cat/master?v
```

```json
id                     host       ip         node
cbwtjwQmR92snUCBlOgfrA 172.19.0.2 172.19.0.2 a97166c104cd
```

## Indices Overview
> Liệt kê list các index trong cluster
```sh
GET _cat/indices?v
```

```json
health status index                                                              uuid                   pri rep docs.count docs.deleted store.size pri.store.size dataset.size
green  open   .internal.alerts-transform.health.alerts-default-000001            1RHO3-zeRf-ZwAPvPF4LqA   1   0          0            0       249b           249b         249b
green  open   .internal.alerts-observability.logs.alerts-default-000001          SF8AwadmRgqXNR87W6BQpA   1   0          0            0       249b           249b         249b
yellow open   aliconcon                                                          eOJVHJUXTjOJOKAWly8MbA   1   1          5            0     11.6kb         11.6kb       11.6kb
green  open   .internal.alerts-observability.uptime.alerts-default-000001        3ZtaGC68SKyJFT874aDg_Q   1   0          0            0       249b           249b         249b
```

## Overview detail index
```sh
GET _cat/indices/aliconcon?v
```

```json
health status index     uuid                   pri rep docs.count docs.deleted store.size pri.store.size dataset.size
yellow open   aliconcon eOJVHJUXTjOJOKAWly8MbA   1   1          5            0     11.6kb         11.6kb       11.6kb
```

with regex
```sh
GET _cat/indices/ali*?v
```

```json
health status index     uuid                   pri rep docs.count docs.deleted store.size pri.store.size dataset.size
yellow open   aliconcon eOJVHJUXTjOJOKAWly8MbA   1   1          5            0     11.6kb         11.6kb       11.6kb
```

## Count all doc tin cluster
```sh
GET _cat/count?v
```

```json
epoch      timestamp count
1734012811 14:13:31  5
```

## Index infor per Shard
> Thông tin index trên từ node (primary + replica)
```sh
GET _cat/shards/aliconcon?v
```

```json
index     shard prirep state      docs  store dataset ip         node
aliconcon 0     p      STARTED       5 11.6kb  11.6kb 172.19.0.2 a97166c104cd
aliconcon 0     r      UNASSIGNED                                
```


## Shard Allocation per Node
```sh
GET _cat/allocation?v
```

```json
shards shards.undesired write_load.forecast disk.indices.forecast disk.indices disk.used disk.avail disk.total disk.percent host       ip         node         node.role
    30                0                 0.0                 2.7mb        2.7mb    55.8gb      951gb   1006.8gb            5 172.19.0.2 172.19.0.2 a97166c104cd cdfhilmrstw
     1                                                                                                                                            UNASSIGNED   
```

## Clear Cache
> Dữ liệu được update hoặc mapping/index thay đổi nhưng result không thay đổi

Xóa toàn bộ cache
```sh
POST _cache/clear?pretty=true
```

```json
{
  "_shards": {
    "total": 16,
    "successful": 15,
    "failed": 0
  }
}
```


Xóa cache của index
```sh
POST /<index>/_cache/clear?pretty=true
POST /aliconcon/_cache/clear?pretty=true
```

```json
{
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  }
}
```

## Cluster setting
> thay đổi các cài đặt (settings) cấp cluster.
```sh
PUT /_cluster/settings
{
  "persistent": {
    "<setting-key>": "<value>"
  },
  "transient": {
    "<setting-key>": "<value>"
  }
}
```

```sh
PUT /_cluster/settings
{
    "persistent" : {
        "search.default_search_timeout" : "50"
    }
}
```


## Create index
```bash
PUT /<index_name>
{
  "settings": {
    "number_of_shards": <num_shards>,
    "number_of_replicas": <num_replicas>
  },
  "mappings": {
    "properties": {
      "<field_name>": {
        "type": "<field_type>"
      }
    }
  },
  "aliases": {
    "<alias_name>": {}
  }
}
```

```sh
PUT /products
{
    "settings": {
        "number_of_shards": 3,
        "number_of_replicas": 1
    },
    "mappings": {
        "properties": {
            "name": {
                "type": "text"
            },
            "price": {
                "type": "float"
            },
            "in_stock": {
                "type": "boolean"
            }
        }
    },
    "aliases": {
        "available_products": {
            "filter": {
                "term": {
                    "in_stock": true
                }
            }
        },
        "cheap_products": {
            "filter": {
                "range": {
                    "price": {
                        "lte": 100
                    }
                }
            }
        }
    }
}
```


```json
{
  "acknowledged": true,
  "shards_acknowledged": true,
  "index": "products"
}
```

## Check shard của index
```sh
GET /_cat/shards/products?v
```

```json
index    shard prirep state      docs store dataset ip         node
products 0     p      STARTED       0  227b    227b 172.19.0.2 a97166c104cd
products 0     r      UNASSIGNED                               
products 1     p      STARTED       0  227b    227b 172.19.0.2 a97166c104cd
products 1     r      UNASSIGNED                               
products 2     p      STARTED       0  227b    227b 172.19.0.2 a97166c104cd
products 2     r      UNASSIGNED                               
```

## Check setting của index

```sh
GET /products/_settings?pretty
```

```json
{
  "products": {
    "settings": {
      "index": {
        "routing": {
          "allocation": {
            "include": {
              "_tier_preference": "data_content"
            }
          }
        },
        "number_of_shards": "3",
        "provided_name": "products",
        "creation_date": "1734014344572",
        "number_of_replicas": "1",
        "uuid": "iXsPAx4KTUi0tqyFKMFERA",
        "version": {
          "created": "8518000"
        }
      }
    }
  }
}
```


## Close Index
>  Tạm thời vô hiệu hóa việc ghi hoặc tìm kiếm dữ liệu trong index. Open để bỏ vô hiệu hóa index
```sh
POST /products/_close
POST /products/_open
```

```json
{
  "acknowledged": true,
  "shards_acknowledged": true,
  "indices": {
    "products": {
      "closed": true
    }
  }
}
```
## Refresh Index
```sh
GET /<index>/_refresh

GET /products/_refresh
```
## Search all
```sh
GET /products/_search?pretty
```

```json
{
  "took": 1,
  "timed_out": false,
  "_shards": {
    "total": 3,
    "successful": 3,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": {
      "value": 1,
      "relation": "eq"
    },
    "max_score": 1,
    "hits": [
      {
        "_index": "products",
        "_id": "bGtnu5MBMTEWbbDb_Cf4",
        "_score": 1,
        "_source": {
          "name": "Laptop",
          "price": 1000,
          "in_stock": "true"
        }
      }
    ]
  }
}
```

## Search by param
```sh
GET /products/_search?q=price:10000&pretty
```

```json
{
  "took": 1,
  "timed_out": false,
  "_shards": {
    "total": 3,
    "successful": 3,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": {
      "value": 1,
      "relation": "eq"
    },
    "max_score": 1,
    "hits": [
      {
        "_index": "products",
        "_id": "bGtnu5MBMTEWbbDb_Cf4",
        "_score": 1,
        "_source": {
          "name": "Laptop",
          "price": 1000,
          "in_stock": "true"
        }
      }
    ]
  }
}
```

## Search doc by id
> Chỉ định id khi created hoặc dùng id tự gen
```sh
GET /products/_doc/bGtnu5MBMTEWbbDb_Cf4?pretty
```

```json
{
  "_index": "products",
  "_id": "bGtnu5MBMTEWbbDb_Cf4",
  "_version": 1,
  "_seq_no": 0,
  "_primary_term": 3,
  "found": true,
  "_source": {
    "name": "Laptop",
    "price": 1000,
    "in_stock": "true"
  }
}
```

## Search match all
```sh
GET /products/_search
{
  "query": {
    "match_all": {}
  }
}
```

```json
{
  "took": 9,
  "timed_out": false,
  "_shards": {
    "total": 3,
    "successful": 3,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": {
      "value": 1,
      "relation": "eq"
    },
    "max_score": 1,
    "hits": [
      {
        "_index": "products",
        "_id": "bGtnu5MBMTEWbbDb_Cf4",
        "_score": 1,
        "_source": {
          "name": "Laptop",
          "price": 1000,
          "in_stock": "true"
        }
      }
    ]
  }
}
```

## Search match one field
> name like "%laptop%" ~ chứa từ "laptop" trong field
```sh
GET /products/_search
{
  "query": {
    "match": {
      "name": "laptop"
    }
  }
}
```

## Search term field
> name like "%laptop%" ~ chứa từ "laptop" trong field
```sh
GET /products/_search
{
  "query": {
    "term": {
      "name": "laptop"
    }
  }
}
```

## Search term field
> name = "laptop" ~ chính xác "laptop" trong field
```sh
GET /products/_search
{
  "query": {
    "term": {
      "name.keyword": "laptop"
    }
  }
}
```

## Search conditions
> AND, OR, NOT logic. Must, should, must_not: Search documents within score of documents
> filter: get all documents match condition filter

### must ~ AND
```sh
GET /products/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "range": {
            "price": {
              "lte": 500
            }
          }
        },
        {
          "term": {
            "in_stock": true
          }
        }
      ]
    }
  }
}
```

### should ~ OR (name like 'laptop' OR price < 300)
```sh
GET /products/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "name": "laptop"
          }
        },
        {
          "range": {
            "price": {
              "lte": 300
            }
          }
        }
      ],
      "minimum_should_match": 1
    }
  }
}
```

### must_not ~ AND NOT ()
```sh
GET /products/_search
{
  "query": {
    "bool": {
      "must_not": [
        {
          "term": {
            "in_stock": false
          }
        }
      ]
    }
  }
}
```

### must + should + must_not
```sh
GET /products/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "range": {
            "price": {
              "lte": 500
            }
          }
        }
      ],
      "should": [
        {
          "match": {
            "name": "laptop"
          }
        },
        {
          "match": {
            "name": "phone"
          }
        }
      ],
      "must_not": [
        {
          "term": {
            "in_stock": false
          }
        }
      ]
    }
  }
}
```

### filter
> Result documents same with must, should but without SCORE
```sh
GET /products/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "range": {
            "price": {
              "lte": 5000
            }
          }
        },
        {
          "term": {
            "in_stock": true
          }
        },
        {
            "match": {
              "name": "pro2023"
            }
        }
      ]
    }
  }
}
```

## Search in range
```sh
GET /products/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 100,
        "lte": 1000
      }
    }
  }
}
```

## Search within wildcard
```sh
GET /products/_search
{
  "query": {
    "wildcard": {
      "name.keyword": "lap*"
    }
  }
}
```

## Search with swrong value of condition
> Tìm kiếm với value có thể sai chính tả
```sh
GET /products/_search
{
  "query": {
    "fuzzy": {
      "name": {
        "value": "laptap",
        "fuzziness": "AUTO"
      }
    }
  }
}
```

## Sort
```sh
GET /products/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "price": {
        "order": "desc"
      }
    },
    {
      "name.keyword": {
        "order": "desc",
        "unmapped_type" : "long"
      }
    }
  ]
}
```

## Limit, size, paging documents
```sh
GET /products/_search
{
  "query": {
    "match_all": {}
  },
  "size": 2
}
```

## aggregation (thống kê)
```sh
GET /products/_search
{
  "size": 0,
  "aggs": {
    "group_by_stock": {
      "terms": {
        "field": "in_stock"
      }
    },
    "price_ranges": {
      "range": {
        "field": "price",
        "ranges": [
          { "to": 50 },
          { "from": 50, "to": 100 },
          { "from": 100 }
        ]
      }
    },
    "average_price": {
      "avg": {
        "field": "price"
      }
    },
    "total_price": {
      "sum": {
        "field": "price"
      }
    },
    "min_price": {
      "min": {
        "field": "price"
      }
    },
    "max_price": {
      "max": {
        "field": "price"
      }
    },
    "price_stats": {
      "stats": {
        "field": "price"
      }
    }
  }
}
```

```json
{
  "took": 1,
  "timed_out": false,
  "_shards": {
    "total": 3,
    "successful": 3,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": {
      "value": 3,
      "relation": "eq"
    },
    "max_score": null,
    "hits": []
  },
  "aggregations": {
    "max_price": {
      "value": 1000
    },
    "price_ranges": {
      "buckets": [
        {
          "key": "*-50.0",
          "to": 50,
          "doc_count": 0
        },
        {
          "key": "50.0-100.0",
          "from": 50,
          "to": 100,
          "doc_count": 0
        },
        {
          "key": "100.0-*",
          "from": 100,
          "doc_count": 3
        }
      ]
    },
    "min_price": {
      "value": 1000
    },
    "total_price": {
      "value": 3000
    },
    "average_price": {
      "value": 1000
    },
    "group_by_stock": {
      "doc_count_error_upper_bound": 0,
      "sum_other_doc_count": 0,
      "buckets": [
        {
          "key": 1,
          "key_as_string": "true",
          "doc_count": 3
        }
      ]
    },
    "price_stats": {
      "count": 3,
      "min": 1000,
      "max": 1000,
      "avg": 1000,
      "sum": 3000
    }
  }
}
```

## Search full-text in one field
> Search name include all words not care order
```sh
GET /products/_search
{
  "query": {
    "match": {
      "name": {
        "query": "pro2021 laptop",
        "operator": "and"
      }
    }
  }
}
```

## Search in multi fields
> Search in 2 field 
```sh
GET /products/_search
{
  "query": {
    "multi_match": {
      "query": "1000 pro2023",
      "fields": ["name", "description"]
    }
  }
}
```
