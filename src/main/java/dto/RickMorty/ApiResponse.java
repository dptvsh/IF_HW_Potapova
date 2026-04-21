package dto.RickMorty;

import java.util.List;

public class ApiResponse<T> {
    public Info info;
    public List<T> results;
}
