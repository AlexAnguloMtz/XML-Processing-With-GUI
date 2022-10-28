package com.aram.sales.repository;

import com.aram.sales.model.SaleRecord;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 *  A DefaultHandler implementation to
 *  parse SaleRecord objects from xml
 *  documents using the SAX API
 *
 * @author Alex Angulo
 */

public class XmlSaleRecordHandler extends DefaultHandler  {

        private static final String ID = "id";
        private static final String FIRST_NAME = "first_name";
        private static final String LAST_NAME = "last_name";
        private static final String SALES = "sales";
        private static final String STATE = "state";
        private static final String DEPARTMENT = "department";
        private static final String SALE_RECORD = "sale_record";

        private final SaleRecord.Builder saleRecordBuilder;
        private List<SaleRecord> saleRecords;
        private StringBuilder elementBuilder;
        private String currentElementBeingParsed;

        public XmlSaleRecordHandler() {
            this.saleRecords = new ArrayList<>();
            this.saleRecordBuilder = SaleRecord.builder();
            this.elementBuilder = new StringBuilder();
        }


        @Override
        public void startDocument() throws SAXException {
            this.saleRecords = new ArrayList<>();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            elementBuilder.append(new String(ch, start, length));
        }

        @Override
        public void startElement(String uri,
                                 String localName,
                                 String qualifiedName,
                                 Attributes attributes) throws SAXException {

            currentElementBeingParsed = qualifiedName;

        }

        @Override
        public void endElement(String uri, String localName, String qualifiedName) throws SAXException {

            String parsedElement = elementBuilder.toString();
            switch (qualifiedName) {
                case ID : saleRecordBuilder.id(parseInt(parsedElement));
                    break;
                case FIRST_NAME : saleRecordBuilder.firstName(parsedElement);
                    break;
                case LAST_NAME : saleRecordBuilder.lastName(parsedElement);
                    break;
                case SALES : saleRecordBuilder.sales(parseDouble(parsedElement));
                    break;
                case STATE : saleRecordBuilder.state(parsedElement);
                    break;
                case DEPARTMENT: saleRecordBuilder.department(parsedElement);
                    break;
                case SALE_RECORD: saleRecords.add(parsedSaleRecord());
            }

            reset(elementBuilder);

        }

        private SaleRecord parsedSaleRecord() {
            return saleRecordBuilder.build();
        }

        private void reset(StringBuilder stringBuilder) {
            stringBuilder.delete(0, stringBuilder.length());
        }

        public List<SaleRecord> getAllSaleRecords() {
            return saleRecords;
        }

}
