namespace UserService.Application.Dtos;

public class Service {
    public string name { get; set; }
    public string version { get; set; }
    public string host  { get; set; }
}

public class Metadata
{
    public string key { get; set; }
    public string value { get; set; }
}

public class LogDto {
    public string message { get; set; }
    public List<Metadata> metadata { get; set; }
    public Service? service { get; set; }
}