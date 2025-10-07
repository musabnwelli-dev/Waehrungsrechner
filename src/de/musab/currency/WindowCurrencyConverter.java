package de.musab.currency;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
/**
 * Ein einfaches Swing-Fenster für einen Währungsrechner.
 */

public class WindowCurrencyConverter {
    private final ICurrencyConverter icurrencyConverter;
    private JTextField fieldAmount;
    private JComboBox<String> sourceCurrency;
    private JLabel resultLabel;
    /**
     * Erzeugt das Fenster und baut die UI auf.
     *
     * @param icurrencyConverter Implementierung der Geschäftslogik/Brücke zu den Convertern
     */
    public WindowCurrencyConverter(ICurrencyConverter icurrencyConverter)
    {
        this.icurrencyConverter = icurrencyConverter;
        JFrame frame = new JFrame("Währungsrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(createOriginalValuePanel(), BorderLayout.NORTH);
        frame.add(createTargetCurrencyPanel(), BorderLayout.CENTER);
        frame.add(createResultPanel(), BorderLayout.SOUTH);
        frame.pack();

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new WindowCurrencyConverter(new CurrencyConversionHandler());


    }
    /**
     * Erzeugt das Panel für den Ausgangswert (Betrag + Ausgangswährung).
     *
     * @return Panel mit Eingabefeld und Ausgangswährungs-ComboBox
     */
    public JPanel createOriginalValuePanel()
    {
        JPanel panel = new JPanel(new FlowLayout());
        fieldAmount = new JTextField(10);
        sourceCurrency = new JComboBox<>();

        sourceCurrency.setModel(new DefaultComboBoxModel<>(icurrencyConverter.getCurrencies()));
        panel.add(fieldAmount);
        panel.add(sourceCurrency);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ausgangswährung"));



        return panel;
    }
    /**
     * Erzeugt das Panel für Zielwährung, Umrechnungsart, Datum und Button.
     *
     * @return Panel mit Zielwährung, Converter-Auswahl, Datum und „Umrechnen“-Button
     */
    public JPanel createTargetCurrencyPanel()
    {
        JPanel panel = new JPanel(new FlowLayout());
        JComboBox<String> targetComboBox = new JComboBox<>();

        targetComboBox.setModel(new DefaultComboBoxModel<>(icurrencyConverter.getCurrencies()));
        panel.add(targetComboBox);
        JComboBox<String> converterComboBox = new JComboBox<>();

        converterComboBox.setModel(new DefaultComboBoxModel<>(icurrencyConverter.getConverters()));
        panel.add(converterComboBox);
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));
        panel.add(spinner);
        JButton umrechnen = new JButton("Umrechnen");
        umrechnen.addActionListener(e -> {
            try {
                icurrencyConverter.setDate((Date) spinner.getValue());
                double result = icurrencyConverter.performConversion(Integer.parseInt(fieldAmount.getText()), (String) sourceCurrency.getSelectedItem(), (String) targetComboBox.getSelectedItem(), (String) converterComboBox.getSelectedItem());
                resultLabel.setText("Ergebnis:" + result);
            } catch (NumberFormatException n)
            {
                JOptionPane.showMessageDialog(panel, "Bitte geben Sie eine gültige Eingabe ein", "Ungültige Eingabe", JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(umrechnen);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Zielwährung"));


        return panel;
    }
    /**
     * Erzeugt das Ergebnis-Panel mit einem Label zur Anzeige des Resultats.
     *
     * @return Panel mit Ergebnis-Label
     */

    public JPanel createResultPanel()
    {
        JPanel panel = new JPanel();
        resultLabel = new JLabel("Ergebnis:");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(resultLabel);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ergebnis"));

        return panel;
    }
}