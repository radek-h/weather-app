package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import net.aksingh.owmjapis.api.APIException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView fImageView1, fImageView2, fImageView3, fImageView4, fImageView5, fImageView6;
    @FXML
    private ImageView fImageView1_2, fImageView2_2, fImageView3_2, fImageView4_2, fImageView5_2, fImageView6_2;
    @FXML
    private ComboBox comboBox1, comboBox2;
    @FXML
    private TextField textField1, textField2;
    @FXML
    private Text textCityName1, textCityName2;
    @FXML
    private Text fTempText1, fTempText2, fTempText3, fTempText4, fTempText5, fTempText6;
    @FXML
    private Text fTempText1_2, fTempText2_2, fTempText3_2, fTempText4_2, fTempText5_2, fTempText6_2;
    @FXML
    private Text fDayName1, fDayName2, fDayName3, fDayName4, fDayName5, fDayName6;
    @FXML
    private Text fDayName1_2, fDayName2_2, fDayName3_2, fDayName4_2, fDayName5_2, fDayName6_2;


    private ImageView[] fImagesView = {fImageView1, fImageView2, fImageView3, fImageView4, fImageView5, fImageView6};
    private Text[] fTempText = { fTempText1, fTempText2, fTempText3, fTempText4, fTempText5, fTempText6};
    private Text[] fDayName = { fDayName1, fDayName2, fDayName3, fDayName4, fDayName5, fDayName6};

    private ImageView[] fImagesView_2 = { fImageView1_2, fImageView2_2, fImageView3_2, fImageView4_2, fImageView5_2, fImageView6_2};
    private Text[] fTempText_2 = {fTempText1_2, fTempText2_2, fTempText3_2, fTempText4_2, fTempText5_2, fTempText6_2};
    private Text[] fDayName_2 = { fDayName1_2, fDayName2_2, fDayName3_2, fDayName4_2, fDayName5_2, fDayName6_2};

    @FXML
    public void searchWeather1() throws IOException, ParseException {

        String selectedCountry = comboBox1.getSelectionModel().getSelectedItem().toString();
        String selectedCity = textField1.getText();
        Weather weather = new Weather(selectedCity,selectedCountry);

        if(!weather.checkCity()) {
            textCityName1.setText("There is no such city");
            fImageView1.setImage(null);
            fTempText1.setText(null);
            for(int i = 0; i < 6; i++){
                fImagesView[i].setImage(null);
                fTempText[i].setText(null);
                fDayName[i].setText(null);
            }
        } else
            textCityName1.setText(selectedCity);

        JSONObject jsonObject = weather.JsonParser();

        ArrayList<String> daysNames = weather.getDayNames(jsonObject);
        fDayName[0] = fDayName1;
        fDayName[1] = fDayName2;
        fDayName[2] = fDayName3;
        fDayName[3] = fDayName4;
        fDayName[4] = fDayName5;
        fDayName[5] = fDayName6;
        for(int i = 0; i < daysNames.size(); i++)
            fDayName[i].setText(daysNames.get(i));

        ArrayList<String> temperatures = weather.getTemperatures(jsonObject);
        fTempText[0] = fTempText1;
        fTempText[1] = fTempText2;
        fTempText[2] = fTempText3;
        fTempText[3] = fTempText4;
        fTempText[4] = fTempText5;
        fTempText[5] = fTempText6;
        for(int i = 0; i < temperatures.size(); i++)
            fTempText[i].setText(temperatures.get(i));

        ArrayList<String> iconCodes = weather.getIconCodes(jsonObject);
        fImagesView[0] = fImageView1;
        fImagesView[1] = fImageView2;
        fImagesView[2] = fImageView3;
        fImagesView[3] = fImageView4;
        fImagesView[4] = fImageView5;
        fImagesView[5] = fImageView6;
        for(int i = 0; i < iconCodes.size(); i++){
            String iconUrl = "http://openweathermap.org/img/w/" + iconCodes.get(i) + ".png";
            fImagesView[i].setImage(new Image(iconUrl));
        }
    }

    @FXML
    public void searchWeather2() throws IOException, ParseException {

        String selectedCountry_2 = comboBox2.getSelectionModel().getSelectedItem().toString();
        String selectedCity_2 = textField2.getText();
        Weather weather = new Weather(selectedCity_2,selectedCountry_2);

        if(!weather.checkCity()) {
            textCityName2.setText("There is no such city");
            fImageView1_2.setImage(null);
            fTempText1_2.setText(null);
            for(int i = 0; i < 6; i++){
                fImagesView_2[i].setImage(null);
                fTempText_2[i].setText(null);
                fDayName_2[i].setText(null);
            }
        } else
            textCityName2.setText(selectedCity_2);

        JSONObject jsonObject = weather.JsonParser();

        ArrayList<String> daysNames = weather.getDayNames(jsonObject);
        fDayName_2[0] = fDayName1_2;
        fDayName_2[1] = fDayName2_2;
        fDayName_2[2] = fDayName3_2;
        fDayName_2[3] = fDayName4_2;
        fDayName_2[4] = fDayName5_2;
        fDayName_2[5] = fDayName6_2;
        for(int i = 0; i < daysNames.size(); i++)
            fDayName_2[i].setText(daysNames.get(i));

        ArrayList<String> temperatures = weather.getTemperatures(jsonObject);
        fTempText_2[0] = fTempText1_2;
        fTempText_2[1] = fTempText2_2;
        fTempText_2[2] = fTempText3_2;
        fTempText_2[3] = fTempText4_2;
        fTempText_2[4] = fTempText5_2;
        fTempText_2[5] = fTempText6_2;
        for(int i = 0; i < temperatures.size(); i++)
            fTempText_2[i].setText(temperatures.get(i));

        ArrayList<String> iconCodes = weather.getIconCodes(jsonObject);
        fImagesView_2[0] = fImageView1_2;
        fImagesView_2[1] = fImageView2_2;
        fImagesView_2[2] = fImageView3_2;
        fImagesView_2[3] = fImageView4_2;
        fImagesView_2[4] = fImageView5_2;
        fImagesView_2[5] = fImageView6_2;
        for(int i = 0; i < iconCodes.size(); i++){
            String iconUrl = "http://openweathermap.org/img/w/" + iconCodes.get(i) + ".png";
            fImagesView_2[i].setImage(new Image(iconUrl));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Afghanistan", "Albania", "Algeria", "American Samoa",
                        "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda",
                        "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
                        "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
                        "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina",
                        "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
                        "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia",
                        "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic",
                        "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
                        "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica",
                        "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark",
                        "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador",
                        "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)",
                        "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
                        "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany",
                        "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala",
                        "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)",
                        "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)",
                        "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
                        "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan",
                        "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
                        "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of",
                        "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique",
                        "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of",
                        "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal",
                        "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria",
                        "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama",
                        "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal",
                        "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis",
                        "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe",
                        "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)",
                        "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
                        "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands",
                        "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan",
                        "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
                        "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
                        "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
                        "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"
                );

        comboBox1.setItems(options);
        comboBox2.setItems(options);
        assignIPtoCity();

    }
    private void assignIPtoCity(){
        GeoIP2 geoip2 = new GeoIP2();
        String cityName = geoip2.getCityName();
        String countryName = geoip2.getCountryName();
        comboBox1.getSelectionModel().select(countryName);
        textField1.setText(cityName);
    }
}
