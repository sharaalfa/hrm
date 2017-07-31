package rostelecom.sha.hrmtech2.generation;

import org.apache.log4j.Logger;
import rostelecom.sha.hrmtech.HTMLParser;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends TimerTask{

    /**
     *  Номер строки
     */
    private int count = 0;

    /**
     * Логгрирование
     */
    private static final Logger log = Logger.getLogger(Main.class);


    /**
     * Создание временного файла и поточная запись переменных данных
     */
    @Override
    public void run() {
        try {
            count++;

            File file = new File("/home/sha/Documents/hrm/temp.txt");

            PrintWriter printWriter = new PrintWriter(file);




            printWriter.write(new HTMLParser().getDataForJSON(
                    "/home/sha/Documents/hrm/betweenness_centrality.csv", count)
            + "\n" + new HTMLParser().getDataForJSON(
                    "/home/sha/Documents/hrm/in_edges_eigenvector_centrality.csv", count) + "\n" +
                    new HTMLParser().getDataForJSON(
                            "/home/sha/Documents/hrm/out_edges_eigenvector_centrality.csv", count) + "\n"+ count);
            printWriter.close();




        } catch (FileNotFoundException e) {
            log.error("нет файла");
        }
        stopRepresent();

    }


    /**
     * Остановка потока
     */
    private void stopRepresent() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error("ошибка таймера");
        }
    }

    /**
     * Создание потока данных
     * @param args
     */
    public static void main(String[] args) {
        TimerTask timerTask = new Main();
        timerTask.run();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1);
        try {
            Thread.sleep(1200000);
        } catch (InterruptedException e) {
            log.error("ошибка потока в таймере");
        }

    }
}
