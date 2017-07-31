package rostelecom.sha.hrmtech2.controller2;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import rostelecom.sha.hrmtech.HTMLParser;
import rostelecom.sha.hrmtech2.controller2.storage.FileSystemStorageService;
import rostelecom.sha.hrmtech2.controller2.storage.StorageFileNotFoundException;
import rostelecom.sha.hrmtech2.controller2.storage.StorageProperties;
import rostelecom.sha.hrmtech2.controller2.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Кнтороллер для spring-boot
 */
@Controller
public class FileUploadController {

    /**
     *  Логгирование
     */
    private static final Logger log = Logger.getLogger(FileUploadController.class);


    /**
     * Исходный файл в формате html  в 100 млн строк для загрузки
     */
    public  String fileName;


    /**
     * Геттер и сеттер
     */
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private final StorageService storageService;

    /**
     * создание процесса для выполнения в Runtime bash-команды
     */
    private Process proccess;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        setFileName(file.getOriginalFilename());
        redirectAttributes.addFlashAttribute("message",
                "Файл " + file.getOriginalFilename() + " загружен успешно!");

        return "redirect:/";
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String handleFileDelete() {
        try {
            //storageService.deleteAll();
            new FileSystemStorageService(new StorageProperties()).deleteAll();
            storageService.init();

        } catch (Exception e) {
            return "Error";
        }
        return "redirect:http://localhost:8080";
    }
    @RequestMapping(value="/graph", method = RequestMethod.GET)
    public String createGraph(RedirectAttributes redirectAttributes) {
        try {
            String command =
                    "bash /home/sha/Yandex.Disk/Java_study/hrmtech2/bash" +
                            "/runPyCreateGraph.sh";
            proccess = Runtime.getRuntime().exec(command);
            if (proccess.waitFor() == 0) {

                redirectAttributes.addFlashAttribute("message",
                        "Граф построен! Данные для графиков готовы!");
                return "redirect:/";

            }

        } catch (InterruptedException e){

        }
        catch (IOException e) {
            return "Error";
        }
        return null;
    }

    @RequestMapping(value="/parse", method = RequestMethod.GET)
    public String doingParse(RedirectAttributes redirectAttributes) {
       new HTMLParser().getMailAndDate(
                "/home/sha/Yandex.Disk/Java_study/hrmtech2/upload-dir/" + getFileName(),
                "cp866", "2016-");
       redirectAttributes.addFlashAttribute("message",
                "Парсинг прошел успешно!");
       return "redirect:/";
    }
    @RequestMapping(value="/mainPage", method = RequestMethod.GET)
    public String getMainSite() {
        return "redirect:http://localhost:8093/";
    }
}
