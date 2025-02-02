package org.anhvt.springbootelk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.SystemHealth;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static net.logstash.logback.marker.Markers.append;


/**
 * Allows to expose actuator metrics
 *
 * @author trunganhvu
 */
@Service
class MetricAndHealthExporterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricAndHealthExporterService.class);

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @Autowired
    private HealthEndpoint healthEndpoint;

    /**
     * Exposes all metrics each 10 minutes after an initial delay of a minute
     */
    @Scheduled(initialDelay = 60000, fixedDelay = 600000)
    void exportMetrics() {
        this.metricsEndpoint.listNames().getNames().forEach(metricName -> {
            var metricResponse = metricsEndpoint.metric(metricName, null);
            if (metricResponse != null && !metricResponse.getMeasurements().isEmpty()) {
                metricResponse.getMeasurements().forEach(measurement ->
                        log(metricName, measurement.getValue())
                );
            }
        });
    }

    /**
     * Pushes heart beats every 10 seconds
     */
    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    void pushHeartbeat() {
        HealthComponent healthComponent = this.healthEndpoint.health();

        if (healthComponent instanceof SystemHealth systemHealth) {
            systemHealth.getComponents().forEach((name, component) -> {
                LOGGER.info("Health Component: {}, Status: {}, Details: {}",
                        name, component.getStatus(), component);
            });
        } else {
            LOGGER.info("Health Status: {}, Details: {}", healthComponent.getStatus(), healthComponent);
        }
    }

    private void log(String metricName, Object metricValue) {
        LOGGER.info(append("metric", metricName), "Reporting metric {}={}", metricName, metricValue);
    }
}
