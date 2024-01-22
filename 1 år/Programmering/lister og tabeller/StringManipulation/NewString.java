
final public class NewString {

    private String str;

    public NewString(String str){

        this.str = str;

    }


    public String shorten(){
        String[] shortened = str.split(" ");
        String newStr = "";
        for(String x : shortened){
            newStr += x.charAt(0);
        }

        return  newStr;
    }

    public String removeChar(String c){

        String newStr = str.replace(c,"");
        
        return newStr;
    }



}
