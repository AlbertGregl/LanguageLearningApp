package algebra.hr.bll.service;

import algebra.hr.dal.entity.Word;

import java.util.List;

public interface WordService extends GenericService<Word>{
    List<Word> getByKeyword(String keyword);
    Word findByWordText(String wordText);
}
