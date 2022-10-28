package com.aram.sales;

import com.aram.sales.app.SalesReportApplication;
import com.aram.sales.gui.SalesDesktopGui;
import com.aram.sales.model.SaleRecordRepository;
import com.aram.sales.repository.XmlSaleRecordRepository;
import com.aram.sales.service.SaleRecordService;
import com.aram.sales.service.SaleRecordServiceImpl;

import static javax.swing.SwingUtilities.invokeLater;

/**
 *  Application architecture and description:
 *
 *  1) Architecture
 *
 *  This application's architecture is based in two general concepts:
 *
 *  The first concept is the 'Ports and Adapters' architecture,
 *  as explained by computer scientist Alistair Cockburn.
 *
 *  - The GUI is relegated to the outermost circle in the architecture.
 *    Only Main knows about its existence.
 *
 *  - Said GUI interacts with the application through a Facade class.
 *
 *  - This Facade uses the Service Layer to complete its duties, which in turn
 *    uses the Repository layer and the Model to achieve its tasks.
 *
 *  - The central point of the application is the Model,
 *    oblivious to any external layer, as it should be.
 *
 *  The second concept is the Domain Driven Design, as explained by Eric Evans.
 *  Every concept in the application has a clear role and all Entities and Value Objects
 *  have a specific class for each of them.
 *
 *  2) Description:
 *
 *  The purpose for this application is to load a group of Sales Reports from
 *  an XML file (included in the root folder of this project) using the Simple
 *  API for XML (SAX), and show in the GUI the following points:
 *      a) Total sales by department
 *      b) Total sales by state
 *      c) Total sales in general
 *
 *  This application could be easily adaptable
 *  to a web format, we would only need to add a REST API layer.
 *
 *  @author Alex Angulo
 */

public class Main {
    
    private static final String FILE_NAME = "sales.xml";

    public static void main(String[] args) {

        SaleRecordRepository saleRecordRepository = new XmlSaleRecordRepository(FILE_NAME);

        SaleRecordService saleRecordService = new SaleRecordServiceImpl(saleRecordRepository);

        SalesReportApplication salesReportApplication = new SalesReportApplication(saleRecordService);

        invokeLater( () -> startGui(salesReportApplication) );

    }

    private static void startGui(SalesReportApplication salesApplication) {
        new SalesDesktopGui(salesApplication).setVisible(true);
    }

}