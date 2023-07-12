package dto;

public class Client2DTO {
    private static String name;

    public Client2DTO(String name) {
        this.name = name;
    }

    public Client2DTO() {

    }


    public static String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "client2DTO{" +
                "name='" + name + '\'' +
                '}';
    }

}
