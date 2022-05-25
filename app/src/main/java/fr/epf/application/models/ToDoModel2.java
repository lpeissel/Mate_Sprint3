package fr.epf.application.models;

public class ToDoModel2 {
    private int id, status;
    private String rappelText, rappelTitre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRappelText() {
        return rappelText;
    }

    public void setRappelText(String rappelText) {
        this.rappelText = rappelText;
    }

    public String getRappelTitre() {
        return rappelTitre;
    }

    public void setRappelTitre(String rappelTitre) {
        this.rappelTitre = rappelTitre;
    }
}
