package com.bsuir.diploma.bonup.service.task.impl;

import com.bsuir.diploma.bonup.dao.organization.OrganizationRepository;
import com.bsuir.diploma.bonup.dao.task.StockNewDao;
import com.bsuir.diploma.bonup.dao.task.StockRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageKeyRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageTranslationRepository;
import com.bsuir.diploma.bonup.dto.converter.task.StockDtoToStockConverter;
import com.bsuir.diploma.bonup.dto.converter.task.StockToPublicStockDtoConverter;
import com.bsuir.diploma.bonup.dto.model.Id;
import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.task.PublicTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.TaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PublicStockDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.StockDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.exception.BaseException;
import com.bsuir.diploma.bonup.exception.organization.NoSuchOrganizationException;
import com.bsuir.diploma.bonup.exception.task.NoSuchStockException;
import com.bsuir.diploma.bonup.exception.task.limit.NumberOfHeavyTasksException;
import com.bsuir.diploma.bonup.exception.task.limit.NumberOfStocksLimitException;
import com.bsuir.diploma.bonup.exception.translation.NoSuchLanguageException;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.validation.DateToBeforeException;
import com.bsuir.diploma.bonup.exception.validation.NegativeNumberException;
import com.bsuir.diploma.bonup.exception.validation.NotPositiveNumberException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Stock;
import com.bsuir.diploma.bonup.model.task.StockNew;
import com.bsuir.diploma.bonup.model.task.TaskNew;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.translation.Language;
import com.bsuir.diploma.bonup.model.translation.LanguageKey;
import com.bsuir.diploma.bonup.model.translation.LanguageTranslation;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.organization.OrganizationContractService;
import com.bsuir.diploma.bonup.service.organization.OrganizationNewService;
import com.bsuir.diploma.bonup.service.organization.OrganizationService;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.task.StockService;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import com.bsuir.diploma.bonup.service.user.ProfileService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationContractService organizationContractService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TranslationService translationService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private OrganizationNewService organizationNewService;
    @Autowired
    private StockNewDao stockNewDao;
    @Autowired
    private PhotoService photoService;

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LanguageKeyRepository languageKeyRepository;
    @Autowired
    private LanguageTranslationRepository languageTranslationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private StockDtoToStockConverter stockDtoToStockConverter;
    @Autowired
    private StockToPublicStockDtoConverter stockToPublicStockDtoConverter;

    @Override
    public long createTaskNew(TaskNewDto taskDto, String lang) {
        UserLogin userLogin = userService.findByToken(taskDto.getToken(), lang);
        OrganizationNew organization = organizationNewService.findByNameAndUser(taskDto.getOrganizationName(), userLogin, lang);

        Timestamp stamp1 = new Timestamp(taskDto.getStartDateTimestamp().longValue() * 1000);
        Date date1 = new Date(stamp1.getTime());
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);

        Timestamp stamp2 = new Timestamp(taskDto.getEndDateTimestamp().longValue() * 1000);
        Date date2 = new Date(stamp2.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        Category category = categoryService.getById(taskDto.getCategoryId(), lang);
        Photo photo = photoService.getPhoto(taskDto.getPhotoId(), lang);


        int currentTaskCount = stockNewDao.findAllByOrganizationNew(organization).size();
        if (currentTaskCount >= organization.getAvailableTasksCount()) {
            throw new NumberOfHeavyTasksException(lang);
        }

        StockNew taskNew = StockNew.builder()
                .title(taskDto.getTitle())
                .organizationNew(organization)
                .category(category)
                .photo(photo)
                .description(taskDto.getDescriptionText())
                .dateFrom(c1)
                .dateTo(c2)
                .build();

        stockNewDao.save(taskNew);

        return taskNew.getId();
    }

    @Override
    public List<PublicTaskNewDto> getAllForOrg(TokenNameOrganization tokenNameOrganization, String lang) {
        UserLogin userLogin = userService.findByToken(tokenNameOrganization.getToken(), lang);
        OrganizationNew organization = organizationNewService.findByNameAndUser(tokenNameOrganization.getName(), userLogin, lang);

        return stockNewDao.findAllByOrganizationNew(organization).stream()
                .map(o -> {
                    PublicTaskNewDto t = PublicTaskNewDto.builder()
                            .descriptionText(o.getDescription())
                            .categoryId(o.getCategory().getId())
//                            .id(o.getId())
                            .photoId(o.getPhoto().getId())
                            .title(o.getTitle())
                            .organizationName(organization.getTitle())
                            .startDateTimestamp(o.getDateFrom().getTimeInMillis() / 1000.0)
                            .endDateTimestamp(o.getDateTo().getTimeInMillis() / 1000.0)
                            .build();
                    return t;
                })
                .collect(Collectors.toList());
    }

    @Override
    public long create(StockDto stockDto, String lang) {
        validateStockDto(stockDto, lang);
        UserLogin user = userService.findByToken(stockDto.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(stockDto.getOrganizationName(), user, lang);

        Calendar instance = today();
        if ((stockDto.getDateTo().after(instance) || equalCalendar(stockDto.getDateTo(), instance))
                && stockDto.getActivity()) {
            Calendar dateFrom = stockDto.getDateFrom().after(instance) ? (Calendar) stockDto.getDateFrom().clone() : instance;
            for (Calendar i = dateFrom; i.before(stockDto.getDateTo()) || equalCalendar(i, stockDto.getDateTo()); i.add(Calendar.DAY_OF_MONTH, 1)) {
                int limitOfStocks = organizationContractService.limitOfStocks(i, organization);
                int activeStocks = getNumberOfActiveStocks(i, organization);
                if (activeStocks >= limitOfStocks) {
                    throw new NumberOfStocksLimitException(lang);
                }
            }
        }

        Stock stock = stockDtoToStockConverter.convert(stockDto);
        Category category = categoryService.getSubCategoryById(stockDto.getSubcategoryId(), lang);
        stock.setOrganization(organization);
        stock.setCategory(category);
        stock.setNameKey("");
        stock.setDescriptionKey("");

        stockRepository.save(stock);

        String nameKey = "stock.name." + stock.getId();
        String descriptionKey = "stock.description." + stock.getId();
        stock.setNameKey(nameKey);
        stock.setDescriptionKey(descriptionKey);
        setNameAndDescriptionKey(nameKey, stockDto.getLangKey(), lang, stockDto.getName());
        setNameAndDescriptionKey(descriptionKey, stockDto.getLangKey(), lang, stockDto.getDescription());

        return stock.getId();
    }

    @Override
    public void setNameAndDescription(SetNameAndDescriptionDto setNameAndDescriptionDto, String lang) {
        Stock stock = validateSetNameAndDescriptionDto(setNameAndDescriptionDto, lang);
        String nameKey = "stock.name." + stock.getId();
        String descriptionKey = "stock.description." + stock.getId();
        setNameAndDescriptionKey(nameKey, setNameAndDescriptionDto.getLangKey(), lang, setNameAndDescriptionDto.getName());
        setNameAndDescriptionKey(descriptionKey, setNameAndDescriptionDto.getLangKey(), lang, setNameAndDescriptionDto.getDescription());
    }

    private Stock validateSetNameAndDescriptionDto(SetNameAndDescriptionDto setNameAndDescriptionDto, String lang) {
        if (Objects.isNull(setNameAndDescriptionDto) || Objects.isNull(setNameAndDescriptionDto.getToken()) || Objects.isNull(setNameAndDescriptionDto.getId())
                || Objects.isNull(setNameAndDescriptionDto.getDescription()) || Objects.isNull(setNameAndDescriptionDto.getName()) || Objects.isNull(setNameAndDescriptionDto.getLangKey())) {
            throw new NullValidationException(lang);
        }
        UserLogin user = userService.findByToken(setNameAndDescriptionDto.getToken(), lang);
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
        Stock stock = stockRepository.findById(setNameAndDescriptionDto.getId())
                .orElseThrow(() -> new NoSuchStockException(lang));
        if (!stock.getOrganization().getUserLogin().equals(user)) {
            throw new AccessErrorException(lang);
        }
        return stock;
    }

    private void setNameAndDescriptionKey(String nameKey, String langKey, String lang, String name) {
        Language language = languageRepository.findByLang(langKey)
                .orElseThrow(() -> new NoSuchLanguageException(lang));

        LanguageKey languageKey = languageKeyRepository.findByKey(nameKey).orElse(null);
        if (languageKey == null) {
            languageKey = new LanguageKey(nameKey);
            languageKeyRepository.save(languageKey);
        }

        LanguageTranslation languageTranslation = languageTranslationRepository.findByLanguageAndLanguageKey(language, languageKey)
                .orElse(null);
        if (languageTranslation == null) {
            languageTranslation = new LanguageTranslation(languageKey, language, name);
            languageTranslationRepository.save(languageTranslation);
        } else {
            languageTranslation.setValue(name);
        }

    }

    @Override
    public int getNumberOfActiveStocks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateTokenNameOrganization(tokenNameOrganization, lang);
        UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(tokenNameOrganization.getName(), user, lang);
        return (int) stockRepository.findByOrganization(organization).stream()
                .filter(stock -> stock.getDateTo().after(today()) || equalCalendar(stock.getDateTo(), today()))
                .filter(Stock::getActivity)
                .count();
    }

    @Override
    public int getNumberOfUnactiveStocks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateTokenNameOrganization(tokenNameOrganization, lang);
        UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(tokenNameOrganization.getName(), user, lang);
        return (int) stockRepository.findByOrganization(organization).stream()
                .filter(stock -> stock.getDateTo().before(today()) || !stock.getActivity())
                .count();
    }

    @Override
    public PublicStockDto getStock(IdToken idToken, String lang) {
        validateIdTokenWithOrWithoutToken(idToken, lang);
        Stock stock = findById(idToken.getId(), lang);
        PublicStockDto publicStockDto = stockToPublicStockDtoConverter.convert(stock);
        publicStockDto.setCategory(translationService.getMessage(publicStockDto.getCategory(), lang));
        publicStockDto.setSubcategory(translationService.getMessage(publicStockDto.getSubcategory(), lang));
        try {
            publicStockDto.setName(translationService.getMessage(publicStockDto.getName(), lang));
            publicStockDto.setDescription(translationService.getMessage(publicStockDto.getDescription(), lang));
        } catch (NoSuchLanguageException e) {
            lang = lang.equals("ru") ? "en" : "ru";
            publicStockDto.setName(translationService.getMessage(publicStockDto.getName(), lang));
            publicStockDto.setDescription(translationService.getMessage(publicStockDto.getDescription(), lang));
        }
        if (Objects.nonNull(idToken.getToken())) {
            UserLogin user = userService.findByToken(idToken.getToken(), lang);
            UserProfile profile = profileService.findByUserLogin(user, lang);
            publicStockDto.setBeloved(profile.getBelovedStocks().contains(stock));
        }
        return publicStockDto;
    }

    @Override
    public List<PublicStockDto> getActiveStocks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateNameDto(tokenNameOrganization, lang);
        Organization organization = organizationRepository.findByName(tokenNameOrganization.getName())
                .orElseThrow(() -> new NoSuchOrganizationException(lang));

        UserProfile profile = null;
        if (Objects.nonNull(tokenNameOrganization.getToken())) {
            UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
            profile = profileService.findByUserLogin(user, lang);
        }

        UserProfile finalProfile = profile;
        return stockRepository.findByOrganization(organization).stream()
                .filter(stock -> stock.getDateTo().after(today())  || equalCalendar(stock.getDateTo(), today()))
                .filter(Stock::getActivity)
                .map(stock -> {
                    PublicStockDto stockDto = stockToPublicStockDtoConverter.convert(stock);
                    if (Objects.nonNull(finalProfile)) {
                        stockDto.setBeloved(finalProfile.getBelovedStocks().contains(stock));
                    }
                    return stockDto;
                })
                .peek(stockDto -> {
                    stockDto.setCategory(translationService.getMessage(stockDto.getCategory(), lang));
                    stockDto.setSubcategory(translationService.getMessage(stockDto.getSubcategory(), lang));
                    try {
                        stockDto.setName(translationService.getMessage(stockDto.getName(), lang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        stockDto.setName(translationService.getMessage(stockDto.getName(), newLang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicStockDto> getUnactiveStocks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateTokenNameOrganization(tokenNameOrganization, lang);
        UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(tokenNameOrganization.getName(), user, lang);

        UserProfile profile = null;
        if (Objects.nonNull(tokenNameOrganization.getToken())) {
            profile = profileService.findByUserLogin(user, lang);
        }

        UserProfile finalProfile = profile;
        return stockRepository.findByOrganization(organization).stream()
                .filter(stock -> stock.getDateTo().before(today()) || !stock.getActivity())
                .map(stock -> {
                    PublicStockDto stockDto = stockToPublicStockDtoConverter.convert(stock);
                    if (Objects.nonNull(finalProfile)) {
                        stockDto.setBeloved(finalProfile.getBelovedStocks().contains(stock));
                    }
                    return stockDto;
                })
                .peek(stockDto -> {
                    stockDto.setCategory(translationService.getMessage(stockDto.getCategory(), lang));
                    stockDto.setSubcategory(translationService.getMessage(stockDto.getSubcategory(), lang));
                    try {
                        stockDto.setName(translationService.getMessage(stockDto.getName(), lang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        stockDto.setName(translationService.getMessage(stockDto.getName(), newLang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    private Stock findById(Long id, String lang) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchStockException(lang));
    }

    private int getNumberOfActiveStocks(Calendar date, Organization organization) {
        return (int) stockRepository.findByOrganization(organization).stream()
                .filter(stock -> (stock.getDateFrom().before(date) && stock.getDateTo().after(date))
                        || equalCalendar(stock.getDateTo(), date)
                        || equalCalendar(stock.getDateFrom(), date))
                .filter(Stock::getActivity)
                .count();
    }

    private void validateStockDto(StockDto stockDto, String lang) {
        if (Objects.isNull(stockDto) || Objects.isNull(stockDto.getName()) || Objects.isNull(stockDto.getDescription()) || Objects.isNull(stockDto.getToken())) {
            throw new NullValidationException(lang);
        }
        if (Objects.isNull(stockDto.getDateFrom()) || Objects.isNull(stockDto.getDateTo()))
            throw new NullValidationException(lang);
        if (stockDto.getDateTo().before(stockDto.getDateFrom()))
            throw new DateToBeforeException(lang);
        if (Objects.isNull(stockDto.getOrganizationName()) || Objects.isNull(stockDto.getSubcategoryId()) || Objects.isNull(stockDto.getActivity()) || Objects.isNull(stockDto.getLangKey()))
            throw new NullValidationException(lang);
    }

    private void validateTokenNameOrganization(TokenNameOrganization tokenNameOrganization, String lang) {
        if (Objects.isNull(tokenNameOrganization) || Objects.isNull(tokenNameOrganization.getToken()) || Objects.isNull(tokenNameOrganization.getName()))
            throw new NullValidationException(lang);
    }

    private void validateNameDto(TokenNameOrganization nameDto, String lang) {
        if (Objects.isNull(nameDto) || Objects.isNull(nameDto.getName()))
            throw new NullValidationException(lang);
    }

    private void validateId(Id id, String lang) {
        if (Objects.isNull(id) || Objects.isNull(id.getId()))
            throw new NullValidationException(lang);
    }

    private void validateIdTokenWithOrWithoutToken(IdToken id, String lang) {
        if (Objects.isNull(id) || Objects.isNull(id.getId()))
            throw new NullValidationException(lang);
    }

    private Calendar today() {
        Calendar instance = Calendar.getInstance();
        String calendarStr = format.format(instance.getTime());
        try {
            instance.setTime(format.parse(calendarStr));
        } catch (ParseException e) {
            throw new BaseException();
        }
        return instance;
    }

    private boolean equalCalendar(Calendar dateTo, Calendar instance) {
        return dateTo.get(Calendar.YEAR) == instance.get(Calendar.YEAR)
                && dateTo.get(Calendar.MONTH) == instance.get(Calendar.MONTH)
                && dateTo.get(Calendar.DAY_OF_MONTH) == instance.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void saveOrUnsave(IdToken idToken, String lang) {
        validateIdToken(idToken, lang);
        UserLogin user = userService.findByToken(idToken.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        Stock stock = findById(idToken.getId(), lang);
        if (profile.getBelovedStocks().contains(stock)) {
            profile.getBelovedStocks().remove(stock);
        } else {
            profile.getBelovedStocks().add(stock);
        }
    }

    private void validateIdToken(IdToken idToken, String lang) {
        if (Objects.isNull(idToken) || Objects.isNull(idToken.getId()) || Objects.isNull(idToken.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public int getNumberOfSavedStocks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return profile.getBelovedStocks().size();
    }

    private void validateTokenUser(TokenDto tokenUser, String lang) {
        if (Objects.isNull(tokenUser) || Objects.isNull(tokenUser.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public List<PublicStockDto> getSavedStocks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return profile.getBelovedStocks().stream()
                .map(stock -> stockToPublicStockDtoConverter.convert(stock))
                .peek(stockDto -> {
                    stockDto.setCategory(translationService.getMessage(stockDto.getCategory(), lang));
                    stockDto.setSubcategory(translationService.getMessage(stockDto.getSubcategory(), lang));
                    try {
                        stockDto.setName(translationService.getMessage(stockDto.getName(), lang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        stockDto.setName(translationService.getMessage(stockDto.getName(), newLang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), newLang));
                    }
                    stockDto.setBeloved(true);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicStockDto> getStocks(PageStockByCategoryDto pageStockByCategoryDto, String lang) {
        validatePageStock(pageStockByCategoryDto, lang);
        List<Category> categories = new ArrayList<>();
        Category category = categoryService.getById(pageStockByCategoryDto.getCategoryId(), lang);
        if (category.getCategory() == null) {
            categories.addAll(categoryService.getSubCategoriesByCategoryId(pageStockByCategoryDto.getCategoryId(), lang));
        } else {
            categories.add(category);
        }

        UserProfile profile = null;
        if (Objects.nonNull(pageStockByCategoryDto.getToken())) {
            UserLogin user = userService.findByToken(pageStockByCategoryDto.getToken(), lang);
            profile = profileService.findByUserLogin(user, lang);
        }
        UserProfile finalProfile = profile;

        return stockRepository.findAllByCategoryInAndDateToGreaterThanEqual(categories, today(),
                PageRequest.of(pageStockByCategoryDto.getPage(), pageStockByCategoryDto.getNumber(), Sort.by("dateTo").ascending())).stream()
                .map(stock -> {
                    PublicStockDto stockDto = stockToPublicStockDtoConverter.convert(stock);
                    if (Objects.nonNull(finalProfile)) {
                        stockDto.setBeloved(finalProfile.getBelovedStocks().contains(stock));
                    }
                    return stockDto;
                })
                .peek(stockDto -> {
                    stockDto.setCategory(translationService.getMessage(stockDto.getCategory(), lang));
                    stockDto.setSubcategory(translationService.getMessage(stockDto.getSubcategory(), lang));
                    try {
                        stockDto.setName(translationService.getMessage(stockDto.getName(), lang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        stockDto.setName(translationService.getMessage(stockDto.getName(), newLang));
                        stockDto.setDescription(translationService.getMessage(stockDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    private void validatePageStock(PageStockByCategoryDto pageStockByCategoryDto, String lang) {
        if (Objects.isNull(pageStockByCategoryDto) || Objects.isNull(pageStockByCategoryDto.getCategoryId()) || Objects.isNull(pageStockByCategoryDto.getPage()) || Objects.isNull(pageStockByCategoryDto.getNumber())) {
            throw new NullValidationException(lang);
        }
        if (pageStockByCategoryDto.getPage() < 0)
            throw new NegativeNumberException(lang);
        if (pageStockByCategoryDto.getNumber() < 1)
            throw new NotPositiveNumberException(lang);
    }

}
