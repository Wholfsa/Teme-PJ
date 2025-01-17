package lab2ex2;

public class Vers {

        private String text;

        public Vers(String text) {
            this.text = text;
        }

        public int numarCuvinte() {
            if (text == null || text.isEmpty()) {
                return 0;
            }
            return text.split("\\s+").length;
        }

        public int numarVocale() {
            int count = 0;
            for (char c : text.toLowerCase().toCharArray()) {
                if ("aeiou".indexOf(c) != -1) {
                    count++;
                }
            }
            return count;
        }

        public boolean seIncheieCu(String grupare) {
            return text.endsWith(grupare);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
