package cn.edu.zut.hw.hanzhaolong;

public class SampleCell {

    private String familyName;
    private String colName;
    private String rowkey;
    private String data;
    @Override
    public String toString() {
        return "SampleCell{" +
                "familyName='" + familyName + '\'' +
                ", colName='" + colName + '\'' +
                ", rowkey='" + rowkey + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

