public class FileService {
    private final FileReader reader;
    private final FileWriter writer;

    public FileService(FileReader reader, FileWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String processFile() {
        String content = reader.read();
        writer.write(content);
        return "Processed " + content;
    }
}
