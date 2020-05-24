package service;

import entity.Document;
import exception.DuplicateKeyException;
import exception.EmptyFieldException;
import javassist.bytecode.stackmap.BasicBlock;
import messages.RootMessages;
import repository.DocumentRepo;
import validators.DocumentValidator;

import javax.swing.*;
import java.util.Set;

public class DocumentService {
    private DocumentRepo documentRepo=new DocumentRepo();

    public void insertNewDocument(Document document)
    {

        DocumentValidator.DocumentValidator(document);


        if(documentRepo.findById(document.getId())==null)
             documentRepo.insertNewDocument(document);
        else
        {
            throw new DuplicateKeyException(RootMessages.DUPLICATE_ENTRY_DOCUMENT);

        }
    }
    public  Document getDocumentById(String documentID)
    {
      return   documentRepo.findById(documentID);
    }
    public Set<Document> getAllDocuments()
    {

        return documentRepo.findAllDocuments();
    }
    public void removeDocument(String docID)
    {

        documentRepo.removeDocument(docID);
    }
    public void updateDocument(Document document)
    {
        documentRepo.updateDocument(document);
    }


}
