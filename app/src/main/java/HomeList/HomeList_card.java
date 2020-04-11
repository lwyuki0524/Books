package HomeList;

public class HomeList_card { //資料結構
    private String date;  /*時間*/
    private String total_exp;         /*當日總支出*/
    private String type;         /*類型*/

    public HomeList_card() {
        super();
    }

    public HomeList_card(String date, String total_exp,String type) {
        super();
        this.date = date;
        this.type = type;
        this.total_exp = total_exp;
    }

    public String getTotalExp() {
        return total_exp;
    }

    public String getdate() {
        return date;
    }

    public String gettype() {
        return type;
    }

    public void setTotalExp(String total_exp) {
        this.total_exp = total_exp;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public void settype(String type) {
        this.type = type;
    }


}
