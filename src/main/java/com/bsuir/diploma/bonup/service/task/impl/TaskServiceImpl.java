package com.bsuir.diploma.bonup.service.task.impl;

import com.bsuir.diploma.bonup.dao.organization.OrganizationRepository;
import com.bsuir.diploma.bonup.dao.task.TaskNewDao;
import com.bsuir.diploma.bonup.dao.task.TaskRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageKeyRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageTranslationRepository;
import com.bsuir.diploma.bonup.dto.converter.task.TaskDtoToTaskConverter;
import com.bsuir.diploma.bonup.dto.converter.task.TaskToPublicTaskDtoConverter;
import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.TokenIdsDro;
import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.task.FinishedTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.NewPublicTaskDto;
import com.bsuir.diploma.bonup.dto.model.task.PublicTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.SavedTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.TaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.employee.EmployeeResolveUserDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PublicStockDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import com.bsuir.diploma.bonup.dto.model.task.task.TaskDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.exception.BaseException;
import com.bsuir.diploma.bonup.exception.organization.NoSuchOrganizationException;
import com.bsuir.diploma.bonup.exception.task.NoSuchStockException;
import com.bsuir.diploma.bonup.exception.task.NoSuchTaskException;
import com.bsuir.diploma.bonup.exception.task.NumberOfTasksLimitException;
import com.bsuir.diploma.bonup.exception.task.TaskAlreadyAcceptedException;
import com.bsuir.diploma.bonup.exception.task.TaskAlreadyDoneException;
import com.bsuir.diploma.bonup.exception.task.TaskAlreadyRejectedException;
import com.bsuir.diploma.bonup.exception.task.TaskNotComeException;
import com.bsuir.diploma.bonup.exception.task.TaskNotUpToDateException;
import com.bsuir.diploma.bonup.exception.task.limit.NumberOfEasyTasksException;
import com.bsuir.diploma.bonup.exception.task.limit.NumberOfHeavyTasksException;
import com.bsuir.diploma.bonup.exception.task.limit.NumberOfMediumTasksException;
import com.bsuir.diploma.bonup.exception.translation.NoSuchLanguageException;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.validation.DateToBeforeException;
import com.bsuir.diploma.bonup.exception.validation.NegativeNumberException;
import com.bsuir.diploma.bonup.exception.validation.NotPositiveNumberException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Employee;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Task;
import com.bsuir.diploma.bonup.model.task.TaskNew;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import com.bsuir.diploma.bonup.model.translation.Language;
import com.bsuir.diploma.bonup.model.translation.LanguageKey;
import com.bsuir.diploma.bonup.model.translation.LanguageTranslation;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserProfile;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.organization.OrganizationContractService;
import com.bsuir.diploma.bonup.service.organization.OrganizationNewService;
import com.bsuir.diploma.bonup.service.organization.OrganizationService;
import com.bsuir.diploma.bonup.service.organization.employee.EmployeeService;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.task.TaskService;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.task.additional.TypeService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import com.bsuir.diploma.bonup.service.user.ProfileService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
public class TaskServiceImpl implements TaskService {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LanguageKeyRepository languageKeyRepository;
    @Autowired
    private LanguageTranslationRepository languageTranslationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private TaskNewDao taskNewDao;
    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
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
    private EmployeeService employeeService;
    @Autowired
    private OrganizationNewService organizationNewService;

    @Autowired
    private TaskDtoToTaskConverter taskDtoToTaskConverter;
    @Autowired
    private TaskToPublicTaskDtoConverter taskToPublicTaskDtoConverter;

    @Override
    public long create(TaskDto taskDto, String lang) {
        validateTaskDto(taskDto, lang);
        UserLogin user = userService.findByToken(taskDto.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(taskDto.getOrganizationName(), user, lang);

        Calendar instance = today();
        Type type = typeService.getById(taskDto.getTypeId(), lang);

        if ((taskDto.getDateTo().after(instance) || equalCalendar(taskDto.getDateTo(), instance))
                && taskDto.getActivity()) {
            Calendar dateFrom = taskDto.getDateFrom().after(instance) ? (Calendar) taskDto.getDateFrom().clone() : instance;

            for (Calendar i = dateFrom; i.before(taskDto.getDateTo()) || equalCalendar(i, taskDto.getDateTo()); i.add(Calendar.DAY_OF_MONTH, 1)) {
                if (type.getId() == 1L) {
                    int activeHeavyTasks = getNumberOfActiveTasks(i, type, organization);
                    int limitOfHeavyTasks = organizationContractService.limitOfHeavyTasks(i, organization);
                    if (activeHeavyTasks >= limitOfHeavyTasks) {
                        throw new NumberOfHeavyTasksException(lang);
                    }
                } else if (type.getId() == 2L) {
                    int activeHeavyTasks = getNumberOfActiveTasks(i, type, organization);
                    int limitOfHeavyTasks = organizationContractService.limitOfMediumTasks(i, organization);
                    if (activeHeavyTasks >= limitOfHeavyTasks) {
                        throw new NumberOfMediumTasksException(lang);
                    }
                } else if (type.getId() == 3L) {
                    int activeHeavyTasks = getNumberOfActiveTasks(i, type, organization);
                    int limitOfHeavyTasks = organizationContractService.limitOfEasyTasks(i, organization);
                    if (activeHeavyTasks >= limitOfHeavyTasks) {
                        throw new NumberOfEasyTasksException(lang);
                    }
                }
            }
        }

        Task task = taskDtoToTaskConverter.convert(taskDto);
        Category category = categoryService.getSubCategoryById(taskDto.getSubcategoryId(), lang);
        task.setOrganization(organization);
        task.setCategory(category);
        task.setType(type);

        taskRepository.save(task);

        String nameKey = "task.name." + task.getId();
        String descriptionKey = "task.description." + task.getId();
        task.setNameKey(nameKey);
        task.setDescriptionKey(descriptionKey);
        setNameAndDescriptionKey(nameKey, taskDto.getLangKey(), lang, taskDto.getName());
        setNameAndDescriptionKey(descriptionKey, taskDto.getLangKey(), lang, taskDto.getDescription());


        return task.getId();
    }

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

        Calendar instance = today();
        if (c2.after(instance)) {
            int currentTaskCount = taskNewDao.findAllByOrganizationNewAndDateToGreaterThanEqual(organization, instance).size();
            if (currentTaskCount >= organization.getAvailableTasksCount()) {
                throw new NumberOfTasksLimitException(lang);
            }
        }

        TaskNew taskNew = TaskNew.builder()
                .title(taskDto.getTitle())
                .organizationNew(organization)
                .category(category)
                .count(taskDto.getAllowedCount())
                .bonus(taskDto.getBonusesCount())
                .photo(photo)
                .description(taskDto.getDescriptionText())
                .dateFrom(c1)
                .dateTo(c2)
                .build();

        taskNewDao.save(taskNew);

        return taskNew.getId();
    }

    private void validateTaskDto(TaskDto taskDto, String lang) {
        if (Objects.isNull(taskDto) || Objects.isNull(taskDto.getName()) || Objects.isNull(taskDto.getDescription()) || Objects.isNull(taskDto.getToken()) || Objects.isNull(taskDto.getCount())) {
            throw new NullValidationException(lang);
        }
        if (Objects.isNull(taskDto.getDateFrom()) || Objects.isNull(taskDto.getDateTo()) || Objects.isNull(taskDto.getLangKey()) || Objects.isNull(taskDto.getActivity()))
            throw new NullValidationException(lang);
        if (taskDto.getDateTo().before(taskDto.getDateFrom()))
            throw new DateToBeforeException(lang);
        if (Objects.isNull(taskDto.getOrganizationName()) || Objects.isNull(taskDto.getSubcategoryId()) || Objects.isNull(taskDto.getTypeId()))
            throw new NullValidationException(lang);
    }

    private int getNumberOfActiveTasks(Calendar date, Type type, Organization organization) {
        return (int) taskRepository.findAllByOrganizationAndType(organization, type).stream()
                .filter(task -> (task.getDateFrom().before(date) && task.getDateTo().after(date))
                        || equalCalendar(task.getDateTo(), date)
                        || equalCalendar(task.getDateFrom(), date))
                .filter(task -> !(task.getCount() <= 0) && task.getActivity())
                .count();
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
    public void setNameAndDescription(SetNameAndDescriptionDto setNameAndDescriptionDto, String lang) {
        Task task = validateSetNameAndDescriptionDto(setNameAndDescriptionDto, lang);
        String nameKey = "task.name." + task.getId();
        String descriptionKey = "task.description." + task.getId();
        setNameAndDescriptionKey(nameKey, setNameAndDescriptionDto.getLangKey(), lang, setNameAndDescriptionDto.getName());
        setNameAndDescriptionKey(descriptionKey, setNameAndDescriptionDto.getLangKey(), lang, setNameAndDescriptionDto.getDescription());
    }

    private Task validateSetNameAndDescriptionDto(SetNameAndDescriptionDto setNameAndDescriptionDto, String lang) {
        if (Objects.isNull(setNameAndDescriptionDto) || Objects.isNull(setNameAndDescriptionDto.getToken()) || Objects.isNull(setNameAndDescriptionDto.getId())
                || Objects.isNull(setNameAndDescriptionDto.getDescription()) || Objects.isNull(setNameAndDescriptionDto.getName()) || Objects.isNull(setNameAndDescriptionDto.getLangKey())) {
            throw new NullValidationException(lang);
        }
        UserLogin user = userService.findByToken(setNameAndDescriptionDto.getToken(), lang);
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
        Task task = taskRepository.findById(setNameAndDescriptionDto.getId())
                .orElseThrow(() -> new NoSuchStockException(lang));
        if (!task.getOrganization().getUserLogin().equals(user)) {
            throw new AccessErrorException(lang);
        }
        return task;
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
    public int getNumberOfActiveTasks(TokenNameOrganization tokenNameOrganization, Type type, String lang) {
        validateTokenNameOrganization(tokenNameOrganization, lang);
        UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(tokenNameOrganization.getName(), user, lang);
        return (int) taskRepository.findAllByOrganizationAndType(organization, type).stream()
                .filter(task -> (task.getDateTo().after(today()) || equalCalendar(task.getDateTo(), today())) && !(task.getCount() <= 0))
                .filter(Task::getActivity)
                .count();
    }

    @Override
    public int getNumberOfActiveTasks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateTokenNameOrganization(tokenNameOrganization, lang);
        UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(tokenNameOrganization.getName(), user, lang);
        return (int) taskRepository.findAllByOrganization(organization).stream()
                .filter(task -> (task.getDateTo().after(today()) || equalCalendar(task.getDateTo(), today())) && !(task.getCount() <= 0))
                .filter(Task::getActivity)
                .count();
    }

    @Override
    public int getNumberOfUnactiveTasks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateTokenNameOrganization(tokenNameOrganization, lang);
        UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(tokenNameOrganization.getName(), user, lang);
        return (int) taskRepository.findAllByOrganization(organization).stream()
                .filter(task -> task.getDateTo().before(today()) || (task.getCount() <= 0) || !task.getActivity())
                .count();
    }

    private void validateTokenNameOrganization(TokenNameOrganization tokenNameOrganization, String lang) {
        if (Objects.isNull(tokenNameOrganization) || Objects.isNull(tokenNameOrganization.getToken()) || Objects.isNull(tokenNameOrganization.getName()))
            throw new NullValidationException(lang);
    }

    @Override
    public PublicTaskDto getTask(IdToken id, String lang) {
        validateId(id, lang);
        Task task = findById(id.getId(), lang);
        PublicTaskDto publicTaskDto = taskToPublicTaskDtoConverter.convert(task);
        publicTaskDto.setCategory(translationService.getMessage(publicTaskDto.getCategory(), lang));
        publicTaskDto.setSubcategory(translationService.getMessage(publicTaskDto.getSubcategory(), lang));
        publicTaskDto.setType(translationService.getMessage(publicTaskDto.getType(), lang));

        try {
            publicTaskDto.setName(translationService.getMessage(publicTaskDto.getName(), lang));
            publicTaskDto.setDescription(translationService.getMessage(publicTaskDto.getDescription(), lang));
        } catch (NoSuchLanguageException e) {
            lang = lang.equals("ru") ? "en" : "ru";
            publicTaskDto.setName(translationService.getMessage(publicTaskDto.getName(), lang));
            publicTaskDto.setDescription(translationService.getMessage(publicTaskDto.getDescription(), lang));
        }

        if (Objects.nonNull(id.getToken())) {
            UserLogin user = userService.findByToken(id.getToken(), lang);
            UserProfile profile = profileService.findByUserLogin(user, lang);
            publicTaskDto.setAccepted(profile.getAcceptedTasks().contains(task));
//            publicTaskDto.setRejected(profile.getRejectedTasks().contains(task));
            publicTaskDto.setDone(profile.getDoneTasks().contains(task));
        }

        return publicTaskDto;
    }

    private Task findById(Long id, String lang) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchTaskException(lang));
    }

    private void validateId(IdToken id, String lang) {
        if (Objects.isNull(id) || Objects.isNull(id.getId()))
            throw new NullValidationException(lang);
    }

    @Override
    public List<PublicTaskNewDto> getAllForOrg(TokenNameOrganization tokenNameOrganization, String lang) {
        UserLogin userLogin = userService.findByToken(tokenNameOrganization.getToken(), lang);
        OrganizationNew organization = organizationNewService.findByNameAndUser(tokenNameOrganization.getName(), userLogin, lang);

        return taskNewDao.findAllByOrganizationNew(organization).stream()
                .map(o -> {
                    PublicTaskNewDto t = PublicTaskNewDto.builder()
                            .allowedCount(o.getCount())
                            .bonusesCount(o.getBonus())
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
    public List<PublicTaskDto> getActiveTasks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateNameDto(tokenNameOrganization, lang);
        Organization organization = organizationRepository.findByName(tokenNameOrganization.getName())
                .orElseThrow(() -> new NoSuchOrganizationException(lang));

        UserProfile profile = null;
        if (Objects.nonNull(tokenNameOrganization.getToken())) {
            UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
            profile = profileService.findByUserLogin(user, lang);
        }
        UserProfile finalProfile = profile;

        return taskRepository.findAllByOrganization(organization).stream()
                .filter(task -> (task.getDateTo().after(today())  || equalCalendar(task.getDateTo(), today())) && !(task.getCount() <= 0))
                .filter(Task::getActivity)
                .map(task -> {
                    PublicTaskDto taskDto = taskToPublicTaskDtoConverter.convert(task);
                    if (Objects.nonNull(finalProfile)) {
                        taskDto.setAccepted(finalProfile.getAcceptedTasks().contains(task));
//                        taskDto.setRejected(finalProfile.getRejectedTasks().contains(task));
                        taskDto.setDone(finalProfile.getDoneTasks().contains(task));
                    }
                    return taskDto;
                })
                .peek(taskDto -> {
                    taskDto.setCategory(translationService.getMessage(taskDto.getCategory(), lang));
                    taskDto.setSubcategory(translationService.getMessage(taskDto.getSubcategory(), lang));
                    taskDto.setType(translationService.getMessage(taskDto.getType(), lang));
                    try {
                        taskDto.setName(translationService.getMessage(taskDto.getName(), lang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        taskDto.setName(translationService.getMessage(taskDto.getName(), newLang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicTaskDto> getActiveTasks(TokenNameOrganization tokenNameOrganization, Type type, String lang) {
        validateNameDto(tokenNameOrganization, lang);
        Organization organization = organizationRepository.findByName(tokenNameOrganization.getName())
                .orElseThrow(() -> new NoSuchOrganizationException(lang));

        UserProfile profile = null;
        if (Objects.nonNull(tokenNameOrganization.getToken())) {
            UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
            profile = profileService.findByUserLogin(user, lang);
        }
        UserProfile finalProfile = profile;

        return taskRepository.findAllByOrganizationAndType(organization, type).stream()
                .filter(task -> (task.getDateTo().after(today())  || equalCalendar(task.getDateTo(), today())) && !(task.getCount() <= 0))
                .filter(Task::getActivity)
                .map(task -> {
                    PublicTaskDto taskDto = taskToPublicTaskDtoConverter.convert(task);
                    if (Objects.nonNull(finalProfile)) {
                        taskDto.setAccepted(finalProfile.getAcceptedTasks().contains(task));
//                        taskDto.setRejected(finalProfile.getRejectedTasks().contains(task));
                        taskDto.setDone(finalProfile.getDoneTasks().contains(task));
                    }
                    return taskDto;
                })
                .peek(taskDto -> {
                    taskDto.setCategory(translationService.getMessage(taskDto.getCategory(), lang));
                    taskDto.setSubcategory(translationService.getMessage(taskDto.getSubcategory(), lang));
                    taskDto.setType(translationService.getMessage(taskDto.getType(), lang));
                    try {
                        taskDto.setName(translationService.getMessage(taskDto.getName(), lang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        taskDto.setName(translationService.getMessage(taskDto.getName(), newLang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicTaskDto> getUnactiveTasks(TokenNameOrganization tokenNameOrganization, String lang) {
        validateTokenNameOrganization(tokenNameOrganization, lang);
        UserLogin user = userService.findByToken(tokenNameOrganization.getToken(), lang);
        organizationService.checkAdminOrganizationPermission(user, lang);
        Organization organization = organizationService.findByNameAndUser(tokenNameOrganization.getName(), user, lang);

        UserProfile profile = null;
        if (Objects.nonNull(tokenNameOrganization.getToken())) {
            profile = profileService.findByUserLogin(user, lang);
        }
        UserProfile finalProfile = profile;

        return taskRepository.findAllByOrganization(organization).stream()
                .filter(task -> task.getDateTo().before(today()) || task.getCount() <= 0)
                .map(task -> {
                    PublicTaskDto taskDto = taskToPublicTaskDtoConverter.convert(task);
                    if (Objects.nonNull(finalProfile)) {
                        taskDto.setAccepted(finalProfile.getAcceptedTasks().contains(task));
//                        taskDto.setRejected(finalProfile.getRejectedTasks().contains(task));
                        taskDto.setDone(finalProfile.getDoneTasks().contains(task));
                    }
                    return taskDto;
                })
                .peek(taskDto -> {
                    taskDto.setCategory(translationService.getMessage(taskDto.getCategory(), lang));
                    taskDto.setSubcategory(translationService.getMessage(taskDto.getSubcategory(), lang));
                    taskDto.setType(translationService.getMessage(taskDto.getType(), lang));
                    try {
                        taskDto.setName(translationService.getMessage(taskDto.getName(), lang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        taskDto.setName(translationService.getMessage(taskDto.getName(), newLang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    private void validateNameDto(TokenNameOrganization nameDto, String lang) {
        if (Objects.isNull(nameDto) || Objects.isNull(nameDto.getName()))
            throw new NullValidationException(lang);
    }

    @Override
    public List<PublicTaskDto> getTasks(PageStockByCategoryDto pageStockByCategoryDto, String lang) {
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

        return taskRepository.findAllByCategoryInAndDateToGreaterThanEqual(categories, today(),
                PageRequest.of(pageStockByCategoryDto.getPage(), pageStockByCategoryDto.getNumber(), Sort.by("dateTo").ascending())).stream()
                .filter(task -> {
                    if (finalProfile == null) {
                        return true;
                    }
                    return !finalProfile.getAcceptedTasks().contains(task)
//                            && !finalProfile.getRejectedTasks().contains(task) && !finalProfile.getDoneTasks().contains(task)
                            ;
                })
                .map(task -> {
                    PublicTaskDto taskDto = taskToPublicTaskDtoConverter.convert(task);
                    if (Objects.nonNull(finalProfile)) {
                        taskDto.setAccepted(finalProfile.getAcceptedTasks().contains(task));
//                        taskDto.setRejected(finalProfile.getRejectedTasks().contains(task));
                        taskDto.setDone(finalProfile.getDoneTasks().contains(task));
                    }
                    return taskDto;
                })
                .peek(taskDto -> {
                    taskDto.setCategory(translationService.getMessage(taskDto.getCategory(), lang));
                    taskDto.setSubcategory(translationService.getMessage(taskDto.getSubcategory(), lang));
                    taskDto.setType(translationService.getMessage(taskDto.getType(), lang));
                    try {
                        taskDto.setName(translationService.getMessage(taskDto.getName(), lang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        taskDto.setName(translationService.getMessage(taskDto.getName(), newLang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<NewPublicTaskDto> tasksCatalog(TokenDto tokenDto, String lang) {
        List<Category> categories;
        UserLogin user = userService.findByToken(tokenDto.getToken(), lang);
        final UserProfile profile = profileService.findByUserLogin(user, lang);
        categories = profile.getCategories();

        List<TaskNew> taskNews = taskNewDao.findAllByCategoryInAndDateToGreaterThanEqual(categories, today())
                .stream()
                .filter(task -> !profile.getAcceptedTasks().contains(task)
                        && !profile.getDoneTasks().contains(task)
                        && !task.getOrganizationNew().getUserLogin().getId().equals(user.getId()))
                .collect(Collectors.toList());
        Collections.shuffle(taskNews);

        return taskNews.stream().limit(25)
                .map(task -> {
                    NewPublicTaskDto publicTaskNewDto = new NewPublicTaskDto();
                    publicTaskNewDto.setName(task.getTitle());
                    publicTaskNewDto.setCategoryId(task.getCategory().getId());
                    publicTaskNewDto.setDescription(task.getDescription());
                    publicTaskNewDto.setId(task.getId());
                    publicTaskNewDto.setOrganizationName(task.getOrganizationNew().getTitle());
                    publicTaskNewDto.setPhotoId(task.getPhoto().getId());
                    publicTaskNewDto.setDateFrom(format.format(task.getDateFrom().getTime()));
                    publicTaskNewDto.setDateTo(format.format(task.getDateTo().getTime()));

                    return publicTaskNewDto;
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

    @Override
    public void acceptTask(IdToken idToken, String lang) {
        validateIdToken(idToken, lang);
        UserLogin user = userService.findByToken(idToken.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);

        Task task = findById(idToken.getId(), lang);
        if (profile.getDoneTasks().contains(task)) {
            throw new TaskAlreadyDoneException(lang);
        }
//        else if (profile.getRejectedTasks().contains(task)) {
//            profile.getRejectedTasks().remove(task);
//        }
        if (profile.getAcceptedTasks().contains(task)) {
            throw new TaskAlreadyAcceptedException(lang);
        }
//        profile.getAcceptedTasks().add(task);
    }

    @Override
    public void acceptTasksNew(TokenIdsDro tokenIdsDro, String lang) {
        UserLogin user = userService.findByToken(tokenIdsDro.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);

        for (Long id : tokenIdsDro.getIds()) {
            TaskNew taskNew = taskNewDao.findById(id)
                    .orElseThrow(() -> new NoSuchTaskException(lang));
            if (profile.getDoneTasks().contains(taskNew)) {
                throw new TaskAlreadyDoneException(lang);
            }
            if (profile.getAcceptedTasks().contains(taskNew)) {
                throw new TaskAlreadyAcceptedException(lang);
            }
            profile.getAcceptedTasks().add(taskNew);
        }
    }

    @Override
    public void rejectTask(IdToken idToken, String lang) {
        validateIdToken(idToken, lang);
        UserLogin user = userService.findByToken(idToken.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);

        Task task = findById(idToken.getId(), lang);
        if (profile.getDoneTasks().contains(task)) {
            throw new TaskAlreadyDoneException(lang);
        } else if (profile.getAcceptedTasks().contains(task)) {
            profile.getAcceptedTasks().remove(task);
        }
//        if (profile.getRejectedTasks().contains(task)) {
//            throw new TaskAlreadyRejectedException(lang);
//        }
//        profile.getRejectedTasks().add(task);
    }

    private void validateIdToken(IdToken idToken, String lang) {
        if (Objects.isNull(idToken) || Objects.isNull(idToken.getId()) || Objects.isNull(idToken.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public int numberOfAcceptedTasks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return profile.getAcceptedTasks().size();
    }

    @Override
    public int numberOfRejectedTasks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
//        return profile.getRejectedTasks().size();
        return 0;
    }

    private void validateTokenUser(TokenDto tokenUser, String lang) {
        if (Objects.isNull(tokenUser) || Objects.isNull(tokenUser.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public List<SavedTaskNewDto> SavedTaskNewDto(TokenDto tokenDto, String lang) {
        validateTokenUser(tokenDto, lang);
        UserLogin user = userService.findByToken(tokenDto.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);

        return profile.getAcceptedTasks().stream()
                .filter(taskNew -> !taskNew.getDateTo().before(today()))
                .map(task -> {
                    NewOrganizationWithPhoto n = new NewOrganizationWithPhoto();
                    n.setAvailableStocksCount(task.getOrganizationNew().getAvailableStocksCount());
                    n.setAvailableCouponsCount(task.getOrganizationNew().getAvailableCouponsCount());
                    n.setAvailableTasksCount(task.getOrganizationNew().getAvailableTasksCount());
                    n.setCategoryId(task.getOrganizationNew().getCategory().getId());
                    n.setContactsPhone(task.getOrganizationNew().getContactsPhone());
                    n.setContactsVK(task.getOrganizationNew().getContactsVK());
                    n.setContactsWebSite(task.getOrganizationNew().getContactsWebSite());
                    n.setDescriptionText(task.getOrganizationNew().getDescriptionText());
                    n.setId(task.getOrganizationNew().getId());
                    n.setTitle(task.getOrganizationNew().getTitle());
                    n.setLatitude(task.getOrganizationNew().getLatitude());
                    n.setPhotoId(task.getOrganizationNew().getPhoto().getId());
                    n.setLongitude(task.getOrganizationNew().getLongitude());
                    n.setDirectorFirstName(task.getOrganizationNew().getDirectorFirstName());
                    n.setDirectorLastName(task.getOrganizationNew().getDirectorLastName());
                    n.setDirectorSecondName(task.getOrganizationNew().getDirectorSecondName());
                    n.setAddress(task.getOrganizationNew().getLocationCountry());

                    return SavedTaskNewDto.builder()
                            .photoId(task.getPhoto().getId())
                            .id(task.getId())
                            .name(task.getTitle())
                            .description(task.getDescription())
                            .dateFrom(format.format(task.getDateFrom().getTime()))
                            .dateTo(format.format(task.getDateTo().getTime()))
                            .categoryId(task.getCategory().getId())
                            .bonusesCount(task.getBonus())
                            .organization(n)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FinishedTaskNewDto> FinishedTaskNewDto(TokenDto tokenDto, String lang) {
        validateTokenUser(tokenDto, lang);
        UserLogin user = userService.findByToken(tokenDto.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);

        List<FinishedTaskNewDto> died = profile.getAcceptedTasks().stream()
                .filter(taskNew -> taskNew.getDateTo().before(today()))
                .map(task -> {
                    NewOrganizationWithPhoto n = new NewOrganizationWithPhoto();
                    n.setAvailableStocksCount(task.getOrganizationNew().getAvailableStocksCount());
                    n.setAvailableCouponsCount(task.getOrganizationNew().getAvailableCouponsCount());
                    n.setAvailableTasksCount(task.getOrganizationNew().getAvailableTasksCount());
                    n.setCategoryId(task.getOrganizationNew().getCategory().getId());
                    n.setContactsPhone(task.getOrganizationNew().getContactsPhone());
                    n.setContactsVK(task.getOrganizationNew().getContactsVK());
                    n.setContactsWebSite(task.getOrganizationNew().getContactsWebSite());
                    n.setDescriptionText(task.getOrganizationNew().getDescriptionText());
                    n.setId(task.getOrganizationNew().getId());
                    n.setTitle(task.getOrganizationNew().getTitle());
                    n.setLatitude(task.getOrganizationNew().getLatitude());
                    n.setPhotoId(task.getOrganizationNew().getPhoto().getId());
                    n.setLongitude(task.getOrganizationNew().getLongitude());
                    n.setDirectorFirstName(task.getOrganizationNew().getDirectorFirstName());
                    n.setDirectorLastName(task.getOrganizationNew().getDirectorLastName());
                    n.setDirectorSecondName(task.getOrganizationNew().getDirectorSecondName());
                    n.setAddress(task.getOrganizationNew().getLocationCountry());

                    return FinishedTaskNewDto.builder()
                            .photoId(task.getPhoto().getId())
                            .id(task.getId())
                            .name(task.getTitle())
                            .description(task.getDescription())
                            .dateFrom(format.format(task.getDateFrom().getTime()))
                            .dateTo(format.format(task.getDateTo().getTime()))
                            .categoryId(task.getCategory().getId())
                            .bonusesCount(task.getBonus())
                            .isDied(true)
                            .isResolved(false)
                            .organization(n)
                            .build();
                })
                .collect(Collectors.toList());

        List<FinishedTaskNewDto> resolved = profile.getDoneTasks().stream()
                .map(task -> {
                    NewOrganizationWithPhoto n = new NewOrganizationWithPhoto();
                    n.setAvailableStocksCount(task.getOrganizationNew().getAvailableStocksCount());
                    n.setAvailableCouponsCount(task.getOrganizationNew().getAvailableCouponsCount());
                    n.setAvailableTasksCount(task.getOrganizationNew().getAvailableTasksCount());
                    n.setCategoryId(task.getOrganizationNew().getCategory().getId());
                    n.setContactsPhone(task.getOrganizationNew().getContactsPhone());
                    n.setContactsVK(task.getOrganizationNew().getContactsVK());
                    n.setContactsWebSite(task.getOrganizationNew().getContactsWebSite());
                    n.setDescriptionText(task.getOrganizationNew().getDescriptionText());
                    n.setId(task.getOrganizationNew().getId());
                    n.setTitle(task.getOrganizationNew().getTitle());
                    n.setLatitude(task.getOrganizationNew().getLatitude());
                    n.setPhotoId(task.getOrganizationNew().getPhoto().getId());
                    n.setLongitude(task.getOrganizationNew().getLongitude());
                    n.setDirectorFirstName(task.getOrganizationNew().getDirectorFirstName());
                    n.setDirectorLastName(task.getOrganizationNew().getDirectorLastName());
                    n.setDirectorSecondName(task.getOrganizationNew().getDirectorSecondName());
                    n.setAddress(task.getOrganizationNew().getLocationCountry());

                    return FinishedTaskNewDto.builder()
                            .photoId(task.getPhoto().getId())
                            .id(task.getId())
                            .name(task.getTitle())
                            .description(task.getDescription())
                            .dateFrom(format.format(task.getDateFrom().getTime()))
                            .dateTo(format.format(task.getDateTo().getTime()))
                            .categoryId(task.getCategory().getId())
                            .bonusesCount(task.getBonus())
                            .isDied(false)
                            .isResolved(true)
                            .organization(n)
                            .build();
                })
                .collect(Collectors.toList());

       died.addAll(resolved);
       return died;
    }

    @Override
    public List<PublicTaskDto> acceptedTasks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return profile.getAcceptedTasks().stream()
                .map(task -> {
                    PublicTaskDto taskDto = null;
//                    PublicTaskDto taskDto = taskToPublicTaskDtoConverter.convert(task);
//                    taskDto.setAccepted(profile.getAcceptedTasks().contains(task));
////                    taskDto.setRejected(profile.getRejectedTasks().contains(task));
//                    taskDto.setDone(profile.getDoneTasks().contains(task));
                    return taskDto;
                })
                .peek(taskDto -> {
                    taskDto.setCategory(translationService.getMessage(taskDto.getCategory(), lang));
                    taskDto.setSubcategory(translationService.getMessage(taskDto.getSubcategory(), lang));
                    taskDto.setType(translationService.getMessage(taskDto.getType(), lang));
                    try {
                        taskDto.setName(translationService.getMessage(taskDto.getName(), lang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), lang));
                    } catch (NoSuchLanguageException e) {
                        String newLang = lang.equals("ru") ? "en" : "ru";
                        taskDto.setName(translationService.getMessage(taskDto.getName(), newLang));
                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), newLang));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicTaskDto> rejectedTasks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return null;
//        return profile.getRejectedTasks().stream()
//                .map(task -> {
//                    PublicTaskDto taskDto = taskToPublicTaskDtoConverter.convert(task);
//                    taskDto.setAccepted(profile.getAcceptedTasks().contains(task));
//                    taskDto.setRejected(profile.getRejectedTasks().contains(task));
//                    taskDto.setDone(profile.getDoneTasks().contains(task));
//                    return taskDto;
//                })
//                .peek(taskDto -> {
//                    taskDto.setCategory(translationService.getMessage(taskDto.getCategory(), lang));
//                    taskDto.setSubcategory(translationService.getMessage(taskDto.getSubcategory(), lang));
//                    taskDto.setType(translationService.getMessage(taskDto.getType(), lang));
//                    try {
//                        taskDto.setName(translationService.getMessage(taskDto.getName(), lang));
//                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), lang));
//                    } catch (NoSuchLanguageException e) {
//                        String newLang = lang.equals("ru") ? "en" : "ru";
//                        taskDto.setName(translationService.getMessage(taskDto.getName(), newLang));
//                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), newLang));
//                    }
//                })
//                .collect(Collectors.toList());
    }

    @Override
    public void canDoneTask(IdToken idToken, String lang) {
        validateIdToken(idToken, lang);
        UserLogin user = userService.findByToken(idToken.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);

        Task task = findById(idToken.getId(), lang);
        if (profile.getDoneTasks().contains(task)) {
            throw new TaskAlreadyDoneException(lang);
        }
        if (task.getDateTo().before(today())) {
            throw new TaskNotUpToDateException(lang);
        }
        if (task.getDateFrom().after(today())) {
            throw new TaskNotComeException(lang);
        }
        if (task.getCount() <= 0) {
            throw new NumberOfTasksLimitException(lang);
        }
    }

    @Override
    public void resolveTask(EmployeeResolveUserDto employeeResolveUserDto, String lang) {
        validateEmployeeResolveUserDto(employeeResolveUserDto, lang);
        canDoneTask(new IdToken(employeeResolveUserDto.getId(), employeeResolveUserDto.getUserToken()), lang);
        UserLogin user = userService.findByToken(employeeResolveUserDto.getUserToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        UserLogin employeeUser = userService.findByToken(employeeResolveUserDto.getEmployeeToken(), lang);
        if (!userService.getUserRole(employeeUser).equals(UserRole.ROLE_EMPLOYEE))
            throw new AccessErrorException(lang);
        if (user.equals(employeeUser)) {
            throw new AccessErrorException(lang);
        }
        Task task = findById(employeeResolveUserDto.getId(), lang);
        List<Employee> employees = employeeService.findByOrganization(task.getOrganization());
        if (!employees.contains(employeeService.findByUserLogin(employeeUser, lang))) {
            throw new AccessErrorException(lang);
        }
        task.setCount(task.getCount() - 1);
//        profile.getDoneTasks().add(task);
//        if (profile.getRejectedTasks().contains(task)) {
//            profile.getRejectedTasks().remove(task);
//        }
        if (profile.getAcceptedTasks().contains(task)) {
            profile.getAcceptedTasks().remove(task);
        }
        profile.setPoints(profile.getPoints() + task.getType().getPointsCount());
    }

    private void validateEmployeeResolveUserDto(EmployeeResolveUserDto employeeResolveUserDto, String lang) {
        if (Objects.isNull(employeeResolveUserDto) || Objects.isNull(employeeResolveUserDto.getId())
                || Objects.isNull(employeeResolveUserDto.getEmployeeToken()) || Objects.isNull(employeeResolveUserDto.getUserToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public long getBalls(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return profile.getPoints();
    }

    @Override
    public List<PublicTaskDto> getDoneTasks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return null;
//        return profile.getDoneTasks().stream()
//                .map(task -> {
//                    PublicTaskDto taskDto = taskToPublicTaskDtoConverter.convert(task);
//                    taskDto.setAccepted(profile.getAcceptedTasks().contains(task));
////                    taskDto.setRejected(profile.getRejectedTasks().contains(task));
//                    taskDto.setDone(profile.getDoneTasks().contains(task));
//                    return taskDto;
//                })
//                .peek(taskDto -> {
//                    taskDto.setCategory(translationService.getMessage(taskDto.getCategory(), lang));
//                    taskDto.setSubcategory(translationService.getMessage(taskDto.getSubcategory(), lang));
//                    taskDto.setType(translationService.getMessage(taskDto.getType(), lang));
//                    try {
//                        taskDto.setName(translationService.getMessage(taskDto.getName(), lang));
//                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), lang));
//                    } catch (NoSuchLanguageException e) {
//                        String newLang = lang.equals("ru") ? "en" : "ru";
//                        taskDto.setName(translationService.getMessage(taskDto.getName(), newLang));
//                        taskDto.setDescription(translationService.getMessage(taskDto.getDescription(), newLang));
//                    }
//                })
//                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfDoneTasks(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        UserProfile profile = profileService.findByUserLogin(user, lang);
        return profile.getDoneTasks().size();
    }

}
