import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Question {

    private String question;
    private String predicate;
    private String filter;
    private String negate_filter;
    private Boolean used;
    private double entropy;
    private Boolean postive;


    Question(String question, String predicate, String filter, String negate_filter)
    {
        this.question=question;
        this.predicate=predicate;
        this.filter=filter;
        this.negate_filter=negate_filter;
        this.used=false;
        this.postive=true;
        this.entropy=0f;
    }

    public String getQuestion() {
        return question;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getFilter() {
        return filter;
    }

    public String getNegate_filter() {
        return negate_filter;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public Boolean getPostive() {
        return postive;
    }

    public void setPostive(Boolean postive) {
        this.postive = postive;
    }
}
