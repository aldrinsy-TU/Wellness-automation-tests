package common;

public class TextMatcher {
    private String text;

    public TextMatcher(String text) {
        this.text = text;
    }

    public static TextMatcher textOf(String text) {
        return new TextMatcher(text);
    }

    public boolean containsIgnoringCase(String expectedText) {
        return text.toLowerCase().contains(expectedText.toLowerCase());
    }

    public static boolean compareStringArrayValSkipRow2(String[] text1, String[] text2){

        boolean ret = true;
        for(int i=0; i<text1.length; i++){
            if(i!=2) {
                if (!text1[i].equalsIgnoreCase(text2[i])) {
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }



}
