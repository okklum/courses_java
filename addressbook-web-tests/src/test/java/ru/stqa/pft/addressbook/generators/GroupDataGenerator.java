package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alisa on 16.05.2016.
 */
public class GroupDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  //библиотека jcommander не поддерживает напрямую работу с файлами, поэтому String
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    //создаем объект текущего класса
    GroupDataGenerator generator = new GroupDataGenerator();
    /* generator - объект, в к-ром заполн-ся соотв.атрибуты; args - параметры из командной строки
    *    new JCommander(generator,args);
     *    расписываем метод подробнее на случай косяка с указанием параметров*/
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException e) {
      //Выводим инфу о доступных опциях при неправильном запуске
      jCommander.usage();
      return;
    }
    
    generator.run();
  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);
    if (format.equals("csv")) {
      saveAsDefoltCSV(groups, new File(file));
    } else if (format.equals("xml")){
      saveAsXML(groups, new File(file));
    } else if (format.equals("json")){
      saveAsJSON(groups, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJSON(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXML(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    /* Один из вариантов написания аннотации для создаваемых данных
    xstream.alias("groups",GroupData.class);
    Второй вариант*/
    xstream.processAnnotations(GroupData.class);
    String xml = xstream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  //Сохранение данных в файл
  private void saveAsDefoltCSV(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupData group: groups) {
      writer.write(String.format("%s;%s;%s;\n", group.getName(),group.getHeader(),group.getFooter()));
    }
    //на случай сбоя: принудительно инициирует запись кешированных данных в файл и завершение операции
    writer.close();
  }

  //Генерация данных
  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
    }
    return groups;
  }
}
