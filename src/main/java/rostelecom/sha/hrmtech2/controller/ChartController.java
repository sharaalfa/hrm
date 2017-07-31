package rostelecom.sha.hrmtech2.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import rostelecom.sha.hrmtech.HTMLParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;


@Controller
public class ChartController {
    private static final Logger log = Logger.getLogger(ChartController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "HRM - технологий и аналитики");
        return "hello";

    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("msg", name);

        return model;

    }
    @RequestMapping(value = "/analyzePageHTML", method = RequestMethod.GET)
    public String pageHTML() {

        return "redirect:http://localhost:8080";
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String pageChart() {
        return "/chart";
    }

    @RequestMapping(value = "/In", method = RequestMethod.GET)
    public String pageChart2() {
        return "/chartIn";
    }

    @RequestMapping(value = "/chrOut", method = RequestMethod.GET)
    public String pageMainFrom() {
        return "redirect:/";
    }


    @RequestMapping(value = "/chartF", method = RequestMethod.GET)
    public String pageChartTwo() {
        return "/chart";
    }

    @RequestMapping(value = "/Out", method = RequestMethod.GET)
    public String pageMainTh() {
        return "/chart2";
    }
    @RequestMapping(value = "/chr", method = RequestMethod.GET)
    public String pageMain() {
        return "redirect:/";
    }


    @RequestMapping(value = "/chIn", method = RequestMethod.GET)
    public String pageChartOne() {
        return "/chartIn";
    }

    @RequestMapping(value = "/ch", method = RequestMethod.GET)
    public String pageMainTo() {
        return "redirect:/";
    }


    /*@RequestMapping(value = "/data.json", method = RequestMethod.GET)
    public String chartShow(Model model) {
        model.addAttribute("betweenness", new HTMLParser().
                getValue("/home/sha/Documents/hrm/temp.txt", 1));
        model.addAttribute("in", new HTMLParser().
                getValue("/home/sha/Documents/hrm/temp.txt", 2));
        model.addAttribute("out", new HTMLParser().
                getValue("/home/sha/Documents/hrm/temp.txt", 3));
        model.addAttribute("departments", new HTMLParser().
                getValue("/home/sha/Documents/hrm/temp.txt", 4));



        while (true)
            return "/data";
    }*/
    @RequestMapping(value = "/data.json", method = RequestMethod.GET)
    public String chartShow(Model model) {
        try {
            File fromXml1 = new File(
                    "/home/sha/Documents/hrm/temp.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(fromXml1);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("doc");

            Node node = nodeList.item(0);
            Element element = (Element) node;
            double betweenness = Double.parseDouble(element.getElementsByTagName("betweenness").item(0).getTextContent());
            String departments = element.getElementsByTagName("departments").item(0).getTextContent();
            double out = Double.parseDouble(element.getElementsByTagName("out").item(0).getTextContent());
            double in = Double.parseDouble(element.getElementsByTagName("in").item(0).getTextContent());
            model.addAttribute("betweenness", betweenness);
            model.addAttribute("departments", departments);
            model.addAttribute("out", out);
            model.addAttribute("in", in);

        } catch (Exception e) {
            log.error("ошибка чтения xml");
        }
        return "/data";


    }
    @RequestMapping(value="/main", method = RequestMethod.GET)
    public String createGraph() {
        try {
            String command =
                    "bash /home/sha/Yandex.Disk/Java_study/hrmtech2/bash" +
                            "/runPyDataForCharts.sh";
            Runtime.getRuntime().exec(command);

        } catch (IOException e) {
            return "redirect:/";
        }
        return "/chart";
    }








}


