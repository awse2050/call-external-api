package com.external.api.domains.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@ToString
public class CCTVStnRoadRequest {

    private String cctvId;
    private String dataType;

    private String timeCode;
    private int pageNo;
    private int numOfRows;

    public String getConvertRequestURI() {
        return "eqmtld=" + this.cctvId + "&" + "dataType=" + this.dataType + "&" +
                "hhCode=" + this.timeCode + "&" + "pageNo=" + this.pageNo + "&" + "numOfRows=" + this.numOfRows;
    }
}
