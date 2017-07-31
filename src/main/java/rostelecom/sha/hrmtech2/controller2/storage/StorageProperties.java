package rostelecom.sha.hrmtech2.controller2.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Создание папки для загрузки исходных данных
     * в формате html-таблицы со 100 и более млн строк
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
