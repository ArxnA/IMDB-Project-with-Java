public class PeopleEditSuggest {
    private People originalPerson;
    private People suggestPerson;

    public PeopleEditSuggest(People originalPerson,People suggestPerson){
        setOriginalPerson(originalPerson);
        setSuggestPerson(suggestPerson);
    }

    public People getOriginalPerson() {
        return originalPerson;
    }

    public void setOriginalPerson(People originalPerson) {
        this.originalPerson = originalPerson;
    }

    public People getSuggestPerson() {
        return suggestPerson;
    }

    public void setSuggestPerson(People suggestPerson) {
        this.suggestPerson = suggestPerson;
    }
}
