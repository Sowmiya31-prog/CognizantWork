package FactoryMethodPatternExample;

// 1. Document interface - defines common behavior for all documents
interface Document {
    void create();
    void open();
    void save();
    void close();
    String getDocumentType();
}

// 2. Concrete Document Classes
class WordDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating a new Word document...");
    }
    
    @Override
    public void open() {
        System.out.println("Opening Word document with Microsoft Word...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Word document as .docx file...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Word document...");
    }
    
    @Override
    public String getDocumentType() {
        return "Word Document (.docx)";
    }
}

class PdfDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating a new PDF document...");
    }
    
    @Override
    public void open() {
        System.out.println("Opening PDF document with PDF reader...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving PDF document as .pdf file...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }
    
    @Override
    public String getDocumentType() {
        return "PDF Document (.pdf)";
    }
}

class ExcelDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating a new Excel document...");
    }
    
    @Override
    public void open() {
        System.out.println("Opening Excel document with Microsoft Excel...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Excel document as .xlsx file...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Excel document...");
    }
    
    @Override
    public String getDocumentType() {
        return "Excel Document (.xlsx)";
    }
}

// 3. Abstract Factory Class
abstract class DocumentFactory {
    // Factory method - to be implemented by concrete factories
    public abstract Document createDocument();
    
    // Template method that uses the factory method
    public Document processDocument() {
        Document document = createDocument();
        document.create();
        return document;
    }
}

// 4. Concrete Factory Classes
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// 5. Document Type Enum for better type safety
enum DocumentType {
    WORD, PDF, EXCEL
}

// 6. Factory Provider Class (Optional - for easier factory selection)
class DocumentFactoryProvider {
    public static DocumentFactory getFactory(DocumentType type) {
        switch (type) {
            case WORD:
                return new WordDocumentFactory();
            case PDF:
                return new PdfDocumentFactory();
            case EXCEL:
                return new ExcelDocumentFactory();
            default:
                throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
}

// 7. Test Class - Main Application
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");
        
        // Method 1: Using concrete factories directly
        System.out.println("1. Creating documents using concrete factories:");
        System.out.println("-".repeat(50));
        
        // Create Word document
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.processDocument();
        System.out.println("Document type: " + wordDoc.getDocumentType());
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();
        
        System.out.println();
        
        // Create PDF document
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.processDocument();
        System.out.println("Document type: " + pdfDoc.getDocumentType());
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();
        
        System.out.println();
        
        // Create Excel document
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.processDocument();
        System.out.println("Document type: " + excelDoc.getDocumentType());
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Method 2: Using factory provider for cleaner code
        System.out.println("2. Creating documents using factory provider:");
        System.out.println("-".repeat(50));
        
        DocumentType[] types = {DocumentType.WORD, DocumentType.PDF, DocumentType.EXCEL};
        
        for (DocumentType type : types) {
            try {
                DocumentFactory factory = DocumentFactoryProvider.getFactory(type);
                Document document = factory.createDocument();
                
                System.out.println("Processing " + document.getDocumentType() + ":");
                document.create();
                document.open();
                document.save();
                document.close();
                System.out.println();
                
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("=".repeat(60));
        
        // Method 3: Demonstrating polymorphism
        System.out.println("\n3. Demonstrating polymorphic behavior:");
        System.out.println("-".repeat(50));
        
        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };
        
        for (DocumentFactory factory : factories) {
            Document doc = factory.createDocument();
            System.out.println("Created: " + doc.getDocumentType());
        }
    }
}

