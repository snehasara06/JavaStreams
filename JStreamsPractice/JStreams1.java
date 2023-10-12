package JStreamsPractice;
import java.util.ArrayList;
import java.util.List;
// import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JStreams1  {
    public static void main(String[] args) {
        ArrayList<Equity> equity=new ArrayList<>();
        Equity eq1= new Equity("MRF","MRF Limited","EQ","18-SEP-1996",10,1,"INE883A01011",10);
        Equity eq2=new Equity("NDTV","New Delhi Television Limited","EQ","19-MAY-2004",4,1,"INE155G01029",4);
        Equity eq3=new Equity("ONGC","Oil & Natural Gas Corporation Limited","EQ","19-JUL-1995",5,1,"INE213A01029",5);
        Equity eq4=new Equity("POLICYBZR","PB Fintech Limited","EQ","15-NOV-2021",2,1,"INE417T01026",2);
        Equity eq5=new Equity("QUICKHEAL","Quick Heal Technologies Limited","EQ","18-FEB-2016",10,1,"INE306L01010",10);
       
        Equity eq6=new Equity("RAINBOW","Rainbow Childrens Medicare Limited","EQ","10-MAY-2022",10,1,"INE961O01016",10);
        Equity eq7=new Equity("SUNTV","Sun TV Network Limited","EQ","24-APR-2006",5,1,"INE424H01027",5);
        Equity eq8=new Equity("TATACOMM","Tata Communications Limited","EQ","12-APR-1995",10,1,"INE151A01013",10);
        Equity eq9=new Equity("WONDERLA","Wonderla Holidays Limited","EQ","09-MAY-2014",10,1,"INE066O01014",10);
        
        Equity eq10=new Equity("ZOMATO","Zomato Limited","EQ","23-JUL-2021",1,1,"INE758T01015",1);
          
       
        equity.add(eq1);
        equity.add(eq2);
        equity.add(eq3);
        equity.add(eq4);
        equity.add(eq5);
        equity.add(eq6);
        equity.add(eq7);
        equity.add(eq8);
        equity.add(eq9);
        equity.add(eq10);
// equity.stream().forEach(System.out::println);

// Predicate pred=new Predicate<Integer>() {

//     @Override
//     public boolean test(Integer t) {
//         throw new UnsupportedOperationException("Unimplemented method 'test'");
//     }
    
// };


        List<Equity> faceValueGreaterThan5= equity.stream()
                                                .filter(u -> u.getFace() > 5)
                                                .collect(Collectors.toList());
        System.out.println("FACE VALUE GREATER THAN 5");
        System.out.println(faceValueGreaterThan5);
        for(Equity e:equity){
            System.out.println(e.symbol+" "+e.companyName+" "+e.series+" "+e.listingDate+" "+e.paidUpValue+" "+e.marketLot+" "+e.isinNo+" "+e.faceValue);

            // System.out.format("%-10s %8s %25s %-10s %8s %8s %8s %8s\n",e.symbol,e.companyName,e.series,e.listingDate,e.paidUpValue,e.marketLot,e.isinNo,e.faceValue);
        }
    }
}
class Equity {
    String symbol = "";
    String companyName = "";
    String series = "";
    String listingDate = "";
    Integer paidUpValue = 0;
    Integer marketLot = 0;
    String isinNo = "";
    Integer faceValue = 0;

    Equity(String sym, String name, String series, String dateList, Integer paidValue, Integer market, String isin,
            Integer face) {
        this.symbol = sym;
        this.companyName = name;
        this.series = series;
        this.listingDate = dateList;
        this.paidUpValue = paidValue;
        this.marketLot = market;
        this.isinNo = isin;
        this.faceValue = face;
    }

    String getSym() {
        return symbol;
    }
    void setSym(String symbol) {
        this.symbol = symbol;
    }

    String getName() {
        return companyName;
    }
    void setName(String company) {
        this.companyName = company;
    }

    String getSeries() {
        return series;
    }
    void setSeries(String series) {
        this.series = series;
    }

    String getListDate() {
        return listingDate;
    }
    void setListDate(String listingDate) {
        this.listingDate = listingDate;
    }

    Integer getPaidValue() {
        return paidUpValue;
    }
    void setPaidValue(Integer paidUpValue) {
        this.paidUpValue = paidUpValue;
    }

    Integer getMarketLot() {
        return marketLot;
    }
    void setMarketLot(Integer marketLot) {
        this.marketLot = marketLot;
    }

    String getIsin() {
        return isinNo;
    }
    void setIsin(String isinNo) {
        this.isinNo = isinNo;
    }

    Integer getFace() {
        return faceValue;
    }
    void setFace(int face) {
        this.faceValue = face;
    }

    @Override
    public String toString() {
        return "Equity [id=" + symbol +
                ", name=" + companyName + ", age=" + faceValue + "]";
    }
}