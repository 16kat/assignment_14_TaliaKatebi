import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MetricConverterGUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: lightblue;");

        // Top Section: Title
        Label title = new Label("Unit Converter");
        title.setStyle("-fx-font-size: 29px; -fx-font-weight: bold; -fx-text-fill: darkblue;");
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        // Center Section: Options and Input/Output
        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(10));

        // Conversion Type Dropdown
        ComboBox<String> conversionType = new ComboBox<>();
        conversionType.getItems().addAll(
                "Length: cm to feet / feet to cm",
                "Weight: kg to lb / lb to kg",
                "Volume: liter to fl oz / fl oz to liter"
        );
        conversionType.setPromptText("Select Conversion Type");

        // Input and Output Fields
        TextField inputField = new TextField();
        inputField.setPromptText("Enter value");
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Conversion Buttons
        Button convertButton = new Button("Convert");
        convertButton.setStyle("-fx-font-size: 14px;");
        convertButton.setDisable(true);

        conversionType.setOnAction(e -> convertButton.setDisable(false)); // Enable button when type is selected

        // Convert Button
        convertButton.setOnAction(e -> {
            String selectedType = conversionType.getValue();
            try {
                double inputValue = Double.parseDouble(inputField.getText());

                switch (selectedType) {
                    case "Length: cm to feet / feet to cm":
                        showLengthOptions(inputValue, resultLabel);
                        break;
                    case "Weight: kg to lb / lb to kg":
                        showWeightOptions(inputValue, resultLabel);
                        break;
                    case "Volume: liter to fl oz / fl oz to liter":
                        showVolumeOptions(inputValue, resultLabel);
                        break;
                    default:
                        resultLabel.setText("Invalid conversion type selected!");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number!");
            }
        });

        centerBox.getChildren().addAll(conversionType, inputField, convertButton, resultLabel);

        root.setCenter(centerBox);

        // Scene and Stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Unit Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLengthOptions(double inputValue, Label resultLabel) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Length Conversion");
        alert.setHeaderText("Choose Conversion Type");
        ButtonType cmToFeet = new ButtonType("cm to feet");
        ButtonType feetToCm = new ButtonType("feet to cm");
        alert.getButtonTypes().setAll(cmToFeet, feetToCm);

        alert.showAndWait().ifPresent(response -> {
            if (response == cmToFeet) {
                double result = centimetersToFeet(inputValue);
                resultLabel.setText(inputValue + " cm = " + result + " feet");
            } else if (response == feetToCm) {
                double result = feetToCentimeters(inputValue);
                resultLabel.setText(inputValue + " feet = " + result + " cm");
            }
        });
    }

    private void showWeightOptions(double inputValue, Label resultLabel) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Weight Conversion");
        alert.setHeaderText("Choose Conversion Type");
        ButtonType kgToLb = new ButtonType("kg to lb");
        ButtonType lbToKg = new ButtonType("lb to kg");
        alert.getButtonTypes().setAll(kgToLb, lbToKg);

        alert.showAndWait().ifPresent(response -> {
            if (response == kgToLb) {
                double result = kilogramsToPounds(inputValue);
                resultLabel.setText(inputValue + " kg = " + result + " lbs");
            } else if (response == lbToKg) {
                double result = poundsToKilograms(inputValue);
                resultLabel.setText(inputValue + " lbs = " + result + " kg");
            }
        });
    }

    private void showVolumeOptions(double inputValue, Label resultLabel) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Volume Conversion");
        alert.setHeaderText("Choose Conversion Type");
        ButtonType literToFlOz = new ButtonType("liter to fl oz");
        ButtonType flOzToLiter = new ButtonType("fl oz to liter");
        alert.getButtonTypes().setAll(literToFlOz, flOzToLiter);

        alert.showAndWait().ifPresent(response -> {
            if (response == literToFlOz) {
                double result = litersToFluidOunces(inputValue);
                resultLabel.setText(inputValue + " liters = " + result + " fl oz");
            } else if (response == flOzToLiter) {
                double result = fluidOuncesToLiters(inputValue);
                resultLabel.setText(inputValue + " fl oz = " + result + " liters");
            }
        });
    }

    // Conversion methods
    public static double centimetersToFeet(double cm) {
        return cm / 30.48;
    }

    public static double feetToCentimeters(double feet) {
        return feet * 30.48;
    }

    public static double kilogramsToPounds(double kg) {
        return kg * 2.20462;
    }

    public static double poundsToKilograms(double lbs) {
        return lbs / 2.20462;
    }

    public static double litersToFluidOunces(double liters) {
        return liters * 33.814;
    }

    public static double fluidOuncesToLiters(double flOz) {
        return flOz / 33.814;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
