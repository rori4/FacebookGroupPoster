import manager.Session;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Session session = new Session();
        session.invokeFacebookSession();
    }
}
