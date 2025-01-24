package com.example.informesgabrielaworld;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class InformeController {
    @FXML
    public TextField TXNombre;
    @FXML
    private Button ButtonClear;

    @FXML
    private Button ButtonGenerate;

    @FXML
    private Button ButtonOpen;

    @FXML
    private Button ButtonEspaña;

    @FXML
    private ComboBox<String> CBCountry;

    @FXML
    private ComboBox<String> CBLanguage;

    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private ToggleGroup Continentes;

    @FXML
    private RadioButton Europe;

    @FXML
    private RadioButton Asia;

    @FXML
    private RadioButton SouthAmerica;

    @FXML
    private RadioButton Oceania;

    @FXML
    private RadioButton NorthAmerica;

    @FXML
    private RadioButton Africa;

    @FXML
    private ImageView imageView;

    @FXML
    private void initialize() {
        //Inicializar paises
        CBCountry.getItems().addAll(
                "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
                "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba",
                "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
                "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan",
                "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil",
                "British Indian Ocean Territory", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
                "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
                "Central African Republic", "Chad", "Chile", "China", "Christmas Island",
                "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
                "Congo, The Democratic Republic of the", "Cook Islands", "Costa Rica", "Croatia",
                "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
                "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
                "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Falkland Islands", "Faroe Islands",
                "Fiji Islands", "Finland", "France", "French Guiana", "French Polynesia",
                "French Southern territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
                "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala",
                "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands",
                "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq",
                "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
                "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
                "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Madagascar", "Malawi",
                "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania",
                "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova", "Monaco",
                "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal",
                "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger",
                "Nigeria", "Niue", "Norfolk Island", "North Korea", "North Macedonia", "Northern Mariana Islands",
                "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay",
                "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Romania",
                "Russian Federation", "Rwanda", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia",
                "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
                "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
                "South Georgia and the South Sandwich Islands", "South Korea", "Spain", "Sri Lanka", "Sudan",
                "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
                "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
                "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
                "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
                "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands, British", "Virgin Islands, U.S.",
                "Western Sahara", "Yemen", "Zambia", "Zimbabwe"
        );

        //Inicializar idiomas
        CBLanguage.getItems().addAll(
                "Afrikaans", "Albanian", "Amharic", "Arabic", "Armenian", "Azerbaijani", "Basque", "Belarusian", "Bengali",
                "Bosnian", "Bulgarian", "Catalan", "Cebuano", "Chichewa", "Chinese", "Corsican", "Croatian", "Czech",
                "Danish", "Dutch", "English", "Esperanto", "Estonian", "Filipino", "Finnish", "French", "Frisian", "Galician",
                "Georgian", "German", "Greek", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian", "Hebrew", "Hindi", "Hmong",
                "Hungarian", "Icelandic", "Igbo", "Indonesian", "Irish", "Italian", "Japanese", "Javanese", "Kannada", "Kazakh",
                "Khmer", "Kinyarwanda", "Korean", "Kurdish (Kurmanji)", "Kyrgyz", "Lao", "Latin", "Latvian", "Lithuanian",
                "Luxembourgish", "Macedonian", "Malagasy", "Malay", "Malayalam", "Maltese", "Maori", "Marathi", "Mongolian",
                "Myanmar (Burmese)", "Nepali", "Norwegian", "Odia (Oriya)", "Pashto", "Persian", "Polish", "Portuguese",
                "Punjabi", "Romanian", "Russian", "Samoan", "Scots Gaelic", "Serbian", "Sesotho", "Shona", "Sindhi",
                "Sinhala", "Slovak", "Slovenian", "Somali", "Spanish", "Sundanese", "Swahili", "Swedish", "Tajik", "Tamil",
                "Tatar", "Telugu", "Thai", "Turkish", "Turkmen", "Ukrainian", "Urdu", "Uyghur", "Uzbek", "Vietnamese",
                "Welsh", "Xhosa", "Yiddish", "Yoruba", "Zulu"
        );
    }

    @FXML
    private void ButonGenerate(ActionEvent actionEvent) {
        //Crear un Task para la barra de tareas que es como un hilo
        Task<Void> generateTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    //Establecer el progreso inicial de la barra de carga
                    updateProgress(0, 100);

                    //Conexión a la base de datos
                    Class.forName("org.mariadb.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/world", "root", "");

                    //Obtener los valores de los combo box
                    String selectedCountry = CBCountry.getValue() != null ? CBCountry.getValue().toString() : null;
                    String selectedLanguage = CBLanguage.getValue() != null ? CBLanguage.getValue().toString() : null;
                    String selectedContinent = null;

                    //Para saber que está seleccionado en el radio button
                    Toggle selectedToggle = Continentes.getSelectedToggle();
                    if (selectedToggle != null) {
                        selectedContinent = ((RadioButton) selectedToggle).getText();
                    }

                    //Guardar los parametros en el mapa
                    Map<String, Object> parametros = new HashMap<>();
                    if (selectedCountry != null) {
                        parametros.put("País", selectedCountry);
                    }
                    if (selectedLanguage != null) {
                        parametros.put("Idioma", selectedLanguage);
                    }
                    if (selectedContinent != null) {
                        parametros.put("Continente", selectedContinent);
                    }

                    for (int i = 1; i <= 4; i++) {
                        //simulamos que tarda un poco
                        Thread.sleep(400);
                        double progress = (double) i / 4;
                        //Actualizamos el progreso por pasos
                        updateProgress(progress, 1);
                    }

                    //La generación de los jaspers
                    JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/ArchivosJasper/InformeGabriela.jasper"),parametros,connection);
                    String nombrePDF=TXNombre.getText();
                    JasperExportManager.exportReportToPdfFile(print,"src/main/resources/InformesGenerados/"+nombrePDF+".pdf");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        //Vinculamos la barra de progreso a la task
        ProgressBar.progressProperty().bind(generateTask.progressProperty());

        // Iniciar el Task en un nuevo hilo
        new Thread(generateTask).start();
    }

    @FXML
    private void ButtonEspaña(ActionEvent actionEvent) {
        Task<Void> generateTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    updateProgress(0, 100);

                    Class.forName("org.mariadb.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/world", "root", "");

                    for (int i = 1; i <= 4; i++) {
                        Thread.sleep(400);
                        double progress = (double) i / 4;
                        updateProgress(progress, 1);
                    }
                    Map<String, Object> parametrosSpain = new HashMap<>();
                    parametrosSpain.put("Continente", "Europe");
                    parametrosSpain.put("País", "Spain");
                    parametrosSpain.put("Idioma", "Spanish");


                    JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/ArchivosJasper/InformeGabrielaSpain.jasper"),parametrosSpain,connection);
                    JasperExportManager.exportReportToPdfFile(print,"src/main/resources/InformesGenerados/InformeSpain.pdf");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        ProgressBar.progressProperty().bind(generateTask.progressProperty());
        new Thread(generateTask).start();
    }


    //Action event que abre la carpeta donde se generan los archivos
    @FXML
    private void ButtonOpen (ActionEvent actionEvent) {
        try {
            File folder = new File("/home/alumno/Escritorio/DAM2/DESARROLLO DE INTERFACES/JASPERWORLD/InformesGabrielaWorld/src/main/resources/InformesGenerados/");
            //File folder = new File("G:\\DAM\\SEGUNDO\\INTERFACES\\SEGUNDO TRIMESTRE\\InformesGabrielaWorld\\src\\main\\resources\\InformesGenerados");

            //Verificamos si la carpeta existe
            if (folder.exists() && folder.isDirectory()) {
                //Si la carpeta existe la abrimos
                //Desktop.getDesktop().open(folder);
                //Para LInux
                Runtime.getRuntime().exec(new String[]{"xdg-open", folder.getAbsolutePath()});
            } else {
                System.out.println("La carpeta no existe.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método para limpiar los campos
    @FXML
    private void ButtonClear(ActionEvent actionEvent) {
        CBCountry.setValue(null);
        CBCountry.setPromptText(CBCountry.getPromptText());
        CBLanguage.setValue(null);
        CBLanguage.setPromptText(CBLanguage.getPromptText());

        if (Continentes.getSelectedToggle() != null) {
            Continentes.getSelectedToggle().setSelected(false);
        }
        ProgressBar.progressProperty().unbind();
        ProgressBar.setProgress(0);
    }
}