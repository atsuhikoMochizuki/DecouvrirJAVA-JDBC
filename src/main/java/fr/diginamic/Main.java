package fr.diginamic;

import fr.diginamic.mochizukiTools.Params;
import fr.diginamic.mochizukiTools.Utils;
import fr.diginamic.service.LogAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger (Main.class);

    public static void main(String[] args) {
//        Utils.clearConsole ();
//        Params.welcomePrompt ();
//        LOG.info ("Bonjour Slf4J !");
//        LOG.info ("Implementation Logback");
        LogAppender.executer ("Démarrage de l'application");
    }
    public static int addition(int a, int b)
    {
        return a+b;
    }
}