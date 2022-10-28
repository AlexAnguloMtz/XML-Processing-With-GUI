package com.aram.sales.repository;

import com.aram.sales.model.SaleRecord;
import com.aram.sales.model.SaleRecordRepository;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 *  A SaleRecordRepository implementation
 *  that loads Sale Records from xml documents.
 *
 * @author Alex Angulo
 */

public class XmlSaleRecordRepository extends DefaultHandler
                                     implements SaleRecordRepository {

        private final XmlSaleRecordHandler xmlHandler;
        private final SAXParser parser;
        private final File file;

        public XmlSaleRecordRepository(String fileName) {
                this.xmlHandler = new XmlSaleRecordHandler();
                this.parser = newParser();
                this.file = new File(fileName);
        }

        @Override
        public List<SaleRecord> findAll() {
                try {
                        return getAllSaleRecords();
                } catch (ParserConfigurationException | SAXException | IOException e) {
                        throw new RuntimeException(e);
                }
        }

        private List<SaleRecord> getAllSaleRecords() throws ParserConfigurationException, SAXException, IOException {
                parser.parse(file, xmlHandler);
                return xmlHandler.getAllSaleRecords();
        }

        private SAXParser newParser() {
                try {
                        return SAXParserFactory.newInstance().newSAXParser();
                } catch (Exception e) {
                        throw new RuntimeException("Unable to create SAX Parser");
                }
        }

}