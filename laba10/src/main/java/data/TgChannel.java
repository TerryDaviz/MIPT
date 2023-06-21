package data;
public class TgChannel {
    private String name;
    private int subscribersAmount;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSubscribersAmount() {
        return subscribersAmount;
    }
    public void setSubscribersAmount(int subscribersAmount) {
        this.subscribersAmount = subscribersAmount;
    }
    public TgChannel(String name, int subscribersAmount) {
        this.name = name;
        this.subscribersAmount = subscribersAmount;
    }
}
