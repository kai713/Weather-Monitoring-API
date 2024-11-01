package ru.kairgaliyev.springCourse.Project3.dto;

import ru.kairgaliyev.springCourse.Project3.models.Measurements;

import java.util.List;

public class MeasurementsResponse {
    List<MeasurementsDTO> list;

    public MeasurementsResponse(List<MeasurementsDTO> list) {
        this.list = list;
    }

    public List<MeasurementsDTO> getList() {
        return list;
    }

    public void setList(List<MeasurementsDTO> list) {
        this.list = list;
    }
}
