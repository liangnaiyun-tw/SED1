import java.util.ArrayList;
import java.util.List;

public class Application {

  private List<Document> documents = new ArrayList<>();

  public void createDocument(String documentType) throws Exception {
    if (documentType.equals("Draw")) {
      this.documents.add(new DrawingDocument());
    } else if (documentType.equals("Text")) {
      this.documents.add(new TextDocument());
    } else {
      throw new Exception(String.format("Document type '%s' does not exist", documentType));
    }
  }

  public void present() {
    for (Document doc : documents) {
      doc.present();
    }
  }
}
