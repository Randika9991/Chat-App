package dto;

public class ClientDTO {
    private static String name;

    public ClientDTO(String name) {
        this.name = name;
    }

    public ClientDTO() {

    }


    public static String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "clientDTO{" +
                "name='" + name + '\'' +
                '}';
    }

}
