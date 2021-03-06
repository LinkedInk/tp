package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagManager;
import seedu.address.model.tag.TagManagerImpl;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final TagManager tagManager;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tagManager = new TagManagerImpl();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
        setTagManager(persons);
    }

    /**
     * Resets the TagManager to contain only tag-person mappings from the given {@code persons} list.
     */
    private void setTagManager(List<Person> persons) {
        this.tagManager.clear();
        this.tagManager.addNewPersonsTags(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTagManager(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if the {@code tag} can be found in the tagManager.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tagManager.hasTag(tag);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        tagManager.addNewPersonTags(p);
    }

    /**
     * Adds a {@code tag} to the designated {@code person}.
     */
    public void addPersonToTag(Tag tag, Person person) {
        Set<Tag> newTagSet = new HashSet<>(person.getTags());
        newTagSet.add(tag);
        Person editedPerson = new Person(person.getName(),
                person.getPhone(),
                person.getEmail(),
                person.getAddress(),
                newTagSet);
        setPerson(person, editedPerson);
    }

    /**
     * Removes {@code tag} from the designated {@code person}.
     */
    public void removePersonFromTag(Tag tag, Person person) {
        Set<Tag> newTagSet = new HashSet<>(person.getTags());
        newTagSet.remove(tag);
        Person editedPerson = new Person(person.getName(),
                person.getPhone(),
                person.getEmail(),
                person.getAddress(),
                newTagSet);
        setPerson(person, editedPerson);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        tagManager.updateExistingPersonTags(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        tagManager.deletePersonTags(key);
    }

    //// util methods

    /**
     * Sorts the address book's internal list according to the given comparator
     */
    public void sortPerson(Comparator<Person> c) {
        persons.sortPersons(c);
    }

    public Set<Person> getPersonsWithTag(Tag tag) {
        return tagManager.getPersonsUnderTag(tag);
    }

    public Set<Tag> getTags() {
        return tagManager.getTags();
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
