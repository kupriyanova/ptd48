package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);
        if(format.equals("json")){
            saveAsJson(contacts, new File(file));
        }else {
            System.out.println("Не известный формат " + format);
        }
    }

    private void saveAsJson(List<ContactData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }
    private List<ContactData> generateContact(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstName("firstName"+ i)
                    .withMidlename("midleName"+ i).withLastname("lastName"+ i)
                    .withMobilePhone("7(123)123123" + i).withFirstEmail("email@email.com" + i));
        }
        return contacts;
    }
}
