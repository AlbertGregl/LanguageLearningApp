package algebra.hr.bll.service;

import algebra.hr.dal.entity.Phrase;

import java.util.List;

public interface PhraseService extends GenericService<Phrase>{
    List<Phrase> getByKeyword(String keyword);
    Phrase findByPhraseText(String phraseText);
}
