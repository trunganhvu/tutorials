package org.anhvt.springbootpdf.service;

import org.anhvt.springbootpdf.entity.Customer;
import org.anhvt.springbootpdf.entity.QuoteItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class CustomerService {
    @Autowired
    private PdfGenerateService pdfGenerateService;

    public void exportCustomer() throws IOException {
        Map<String, Object> data = new HashMap<>();
        Customer customer = new Customer("anhvt", "anhvt",
                "VN", "anhvt@email.com", "0000");
        data.put("customer", customer);

        List<QuoteItem> quoteItems = new ArrayList<>();
        QuoteItem quoteItem1 = new QuoteItem("Test Quote Item 1", 1, 100.0, 100.0);
        QuoteItem quoteItem2 = new QuoteItem("Test Quote Item 2", 2, 200.0, 100.0);
        quoteItems.add(quoteItem1);
        quoteItems.add(quoteItem2);


        data.put("quoteItems", quoteItems);

        pdfGenerateService.generatePdfFile("quotation", data, "quotation.pdf");
    }
}
