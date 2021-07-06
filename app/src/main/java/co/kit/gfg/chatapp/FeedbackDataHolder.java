package co.kit.gfg.chatapp;

public class FeedbackDataHolder {
    String name, email, suggestions;
    float ui, performance, build, connectivity, overall_experience;

    public FeedbackDataHolder(String name, String email, String suggestions, float ui,
                              float performance, float build, float connectivity,
                              float overall_experience) {
        this.name = name;
        this.email = email;
        this.suggestions = suggestions;
        this.ui = ui;
        this.performance = performance;
        this.build = build;
        this.connectivity = connectivity;
        this.overall_experience = overall_experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public float getUi() {
        return ui;
    }

    public void setUi(float ui) {
        this.ui = ui;
    }

    public float getPerformance() {
        return performance;
    }

    public void setPerformance(float performance) {
        this.performance = performance;
    }

    public float getBuild() {
        return build;
    }

    public void setBuild(float build) {
        this.build = build;
    }

    public float getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(float connectivity) {
        this.connectivity = connectivity;
    }

    public float getOverall_experience() {
        return overall_experience;
    }

    public void setOverall_experience(float overall_experience) {
        this.overall_experience = overall_experience;
    }
}
