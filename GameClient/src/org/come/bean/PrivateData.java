package org.come.bean;

public class PrivateData
{
    private String taskComplete;
    private String taskData;
    private String Skills;
    private String born;
    private String born1;
    private String TimingGood;
    
    public String getBorn1() {
        return this.born1;
    }
    
    public void setBorn1(String born1) {
        this.born1 = born1;
    }
    
    public String getTaskComplete() {
        return this.taskComplete;
    }
    
    public void setTaskComplete(String taskComplete) {
        this.taskComplete = taskComplete;
    }
    
    public String getTaskData() {
        return this.taskData;
    }
    
    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }
    
    public String getSkills() {
        return (this.Skills == null) ? "" : this.Skills;
    }
    
    public String[] getSkill(String type) {
        if (this.Skills != null && !this.Skills.equals("")) {
            String[] vs = this.Skills.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith(type)) {
                    return vs[i].substring(1).split("#");
                }
            }
            return null;
        }
        else {
            return null;
        }
    }
    
    public String getSkill(String type, String skillId) {
        if (this.Skills != null && !this.Skills.equals("")) {
            String[] vs = this.Skills.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith(type)) {
                    String[] split;
                    for (String s : split = vs[i].substring(1).split("#")) {
                        if (s.startsWith(skillId)) {
                            return s.split("_")[1];
                        }
                    }
                }
            }
            return null;
        }
        else {
            return null;
        }
    }
    
    public void setSkills(String type, String skill) {
        if (this.Skills != null && !this.Skills.equals("")) {
            StringBuffer buffer = new StringBuffer();
            String[] vs = this.Skills.split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (!vs[i].startsWith(type)) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[i]);
                }
            }
            if (skill != null && !skill.equals("")) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(type);
                buffer.append(skill);
            }
            this.Skills = buffer.toString();
        }
        else if (skill != null && !skill.equals("")) {
            this.Skills = type + skill;
        }
    }
    
    public void setSkills(String skills) {
        this.Skills = skills;
    }
    
    public String getBorn() {
        return this.born;
    }
    
    public void setBorn(String born) {
        this.born = born;
    }
    
    public String getTimingGood() {
        return this.TimingGood;
    }
    
    public void setTimingGood(String timingGood) {
        this.TimingGood = timingGood;
    }
}
