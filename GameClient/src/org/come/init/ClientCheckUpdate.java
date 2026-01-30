package org.come.init;

import org.json.JSONException;
import org.json.JSONArray;
import org.come.log.UpdateNewFile;
import com.main.UpdateMain;
import org.json.JSONObject;

public class ClientCheckUpdate
{
    private Boolean needUpdate;
    private Boolean failure;
    private Boolean done;
    private JSONObject jsonObject;
    private String version;
    private UpdateView updateView;
    
    public ClientCheckUpdate(UpdateView updateView) {
        this.needUpdate = Boolean.valueOf(false);
        this.failure = Boolean.valueOf(false);
        this.done = Boolean.valueOf(false);
        this.updateView = updateView;
    }
    
    public void start() {
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.version = getVersion();
        UpdateNewFile.updateNewexe(UpdateMain.updateIP);
    }
    
    public Boolean isFailure() {
        return this.failure;
    }
    
    public Boolean isDone() {
        return this.done;
    }
    
    public Boolean isNeedUpdate() {
        return this.needUpdate;
    }
    
    public Boolean startUpdate() {
        if ((boolean)this.isNeedUpdate() && this.updateView != null) {
            try {
                JSONArray JSONArray = this.jsonObject.getJSONArray("versions");
                for (int i = 0; i < JSONArray.length(); ++i) {
                    JSONArray list = (JSONArray)JSONArray.get(i);
                    this.updateView.getTitle().setText("开始下载更新包:" + list.get(0));
                    UpdatePack updatePack = new UpdatePack((String)list.get(0), this.updateView, Long.parseLong(list.get(1) + ""));
                }
                return Boolean.valueOf(true);
            }
            catch (JSONException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
        return Boolean.valueOf(false);
    }
    
    public static String getVersion() {
        String cen = Tool.readToString(System.getProperty("user.dir") + "/resource/other/ifupdate.txt");
        return cen;
    }
}
