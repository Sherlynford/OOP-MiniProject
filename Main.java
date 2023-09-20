import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract String makeSound();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

interface Flyable {
    String fly();
}

class Mammal extends Animal {
    public Mammal(String name, int age) {
        super(name, age);
    }

    @Override
    public String makeSound() {
        return getName() + " makes a mammal sound.";
    }

    public String run() {
        return getName() + " is running.";
    }
}

class Bird extends Animal implements Flyable {
    public Bird(String name, int age) {
        super(name, age);
    }

    @Override
    public String makeSound() {
        return getName() + " makes a bird sound.";
    }

    @Override
    public String fly() {
        return getName() + " is flying.";
    }
}

class Reptile extends Animal {
    public Reptile(String name, int age) {
        super(name, age);
    }

    @Override
    public String makeSound() {
        return getName() + " makes a reptile sound.";
    }

    public String bite() {
        return getName() + " is biting.";
    }
}

class Fish extends Animal {
    public Fish(String name, int age) {
        super(name, age);
    }

    @Override
    public String makeSound() {
        return getName() + " makes a bubbling sound.";
    }

    public String swim() {
        return getName() + " is swimming.";
    }
}

class Amphibian extends Animal {
    public Amphibian(String name, int age) {
        super(name, age);
    }

    @Override
    public String makeSound() {
        return getName() + " makes an amphibian sound.";
    }

    public String swim() {
        return getName() + " is swimming.";
    }

    public String jump() {
        return getName() + " jumps out of the water.";
    }
}

class Insect extends Animal {
    public Insect(String name, int age) {
        super(name, age);
    }

    @Override
    public String makeSound() {
        return getName() + " makes an insect sound.";
    }

    public String fly() {
        return getName() + " is flying.";
    }
}

class Zoo {
    private ArrayList<Animal> animals;

    public Zoo() {
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}

class AnimalGUI extends JFrame {
    private JTextArea textArea;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> typeComboBox;
    private Zoo zoo;

    public AnimalGUI() {
        setTitle("Zoo Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        zoo = new Zoo();

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        JLabel typeLabel = new JLabel("Type:");
        String[] animalTypes = {"Mammal", "Bird", "Reptile", "Fish", "Amphibian", "Insect"};
        typeComboBox = new JComboBox<>(animalTypes);

        JButton addButton = new JButton("Add Animal");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnimal();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        JButton showButton = new JButton("Show Animal Behaviors");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAnimalBehaviors();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addAnimal() {
        String name = nameField.getText();
        int age;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Invalid Age", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedType = (String) typeComboBox.getSelectedItem();
        Animal animal;

        switch (selectedType.toLowerCase()) {
            case "mammal":
                animal = new Mammal(name, age);
                break;
            case "bird":
                animal = new Bird(name, age);
                break;
            case "reptile":
                animal = new Reptile(name, age);
                break;
            case "fish":
                animal = new Fish(name, age);
                break;
            case "amphibian":
                animal = new Amphibian(name, age);
                break;
            case "insect":
                animal = new Insect(name, age);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid animal type.", "Invalid Type", JOptionPane.ERROR_MESSAGE);
                return;
        }

        zoo.addAnimal(animal);
        nameField.setText("");
        ageField.setText("");
        JOptionPane.showMessageDialog(this, "Animal added successfully!", "Animal Added", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayAnimalBehaviors() {
        textArea.setText(""); 

        for (Animal animal : zoo.getAnimals()) {
            textArea.append(animal.getName() + ", Age: " + animal.getAge() + "\n");
            textArea.append(animal.makeSound() + "\n");

            if (animal instanceof Flyable) {
                textArea.append(((Flyable) animal).fly() + "\n");
            }

            if (animal instanceof Mammal) {
                textArea.append(((Mammal) animal).run() + "\n");
            }

            if (animal instanceof Reptile) {
                textArea.append(((Reptile) animal).bite() + "\n");
            }

            if (animal instanceof Fish) {
                textArea.append(((Fish) animal).swim() + "\n");
            }

            if (animal instanceof Amphibian) {
                textArea.append(((Amphibian) animal).swim() + "\n");
                textArea.append(((Amphibian) animal).jump() + "\n");
            }

            if (animal instanceof Insect) {
                textArea.append(((Insect) animal).fly() + "\n");
            }

            textArea.append("\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AnimalGUI animalGUI = new AnimalGUI();
                animalGUI.setVisible(true);
            }
        });
    }
}
