package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(groups, new File(file));
  }

  //Сохранение данных в файл
  private void save(List<GroupData> groups, File file) throws IOException {
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
