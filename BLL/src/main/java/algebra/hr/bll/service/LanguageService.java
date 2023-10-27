package algebra.hr.bll.service;

import algebra.hr.dal.entity.Language;

import java.util.List;

public interface LanguageService extends GenericService<Language>{
    List<Language> getByKeyword(String keyword);
}
