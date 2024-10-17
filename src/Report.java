public class Report {
    Object content;
    String reportText;

    public Report(String text,Object content){
        setContent(content);
        setReportText(text);
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
}
