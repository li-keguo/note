/**
 * FinallyDemo.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println(testFinally());
        final FinallyBean test = testFinallyBean();
        System.out.println(test.getName());
    }
    
    public static String testFinally() {
        String str = "first";
        try {
            return str;
        } finally {
            str = "finally update";
            System.out.println("testFinally finally exec...");
        }
    }
    
    public static FinallyBean testFinallyBean() {
        final FinallyBean finallyBean = new FinallyBean("first");
        try {
            return finallyBean;
        } finally {
            finallyBean.setName("finally update");
        }
        
    }
    
    
    static class FinallyBean {
        private String name;
        
        public FinallyBean(String name) {
            this.name = name;
        }
        
        /**
         * get name.
         */
        public String getName() {
            return name;
        }
        
        /**
         * set name.
         */
        public void setName(String name) {
            this.name = name;
        }
    }
    
}
