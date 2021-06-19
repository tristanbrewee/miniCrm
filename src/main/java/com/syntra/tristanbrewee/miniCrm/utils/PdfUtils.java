package com.syntra.tristanbrewee.miniCrm.utils;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.syntra.tristanbrewee.miniCrm.model.*;
import com.syntra.tristanbrewee.miniCrm.model.dtos.CompletePerson;
import com.syntra.tristanbrewee.miniCrm.persistance.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PdfUtils {

    private PersonServiceImp personServiceImp;
    private MemberServiceImp memberServiceImp;
    private CompleteClassesServiceImp completeClassesServiceImp;
    private final String OUTPUT_PATH = "src/main/java/com/syntra/tristanbrewee/miniCrm/output/";

    public PdfUtils() {
    }

    public String eventToAddressListPdf(Event event){
        try {
            String filename = event.getDescription() + event.getEventDate().toString();
            String completePath = OUTPUT_PATH + filename + ".pdf";
            PdfDocument pdf = new PdfDocument(new PdfWriter(completePath));
            Document document = new Document(pdf);
            List<Person> persons = getPersonsFromEvent(event);
            Table table = new Table(2);
            persons.stream()
                    .forEach(e -> {
                        table.addCell(new Paragraph(getLabelsFromPerson(e)).setKeepTogether(true));
                    });
            document.add(table);
            document.close();
            return completePath;
        }
        catch (IOException | FileNotFoundException e){
            return e.getMessage();
        }
    }

    private String getLabelsFromPerson(Person person){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < person.getPerson_address().size(); i++){
            if (AddressUtils.checkIfAllFieldsAreNull(person.getPerson_address().get(i)))
                continue;
            output.append(person.getFirstName() + " " + person.getLastName());
            output.append("\n");
            output.append(person.getPerson_address().get(i).getStreet() + " " + person.getPerson_address().get(i).getHouseNumber());
            output.append("\n");
            String bus = person.getPerson_address().get(i).getBus();
            if (bus != null){
                output.append(person.getPerson_address().get(i).getBus());
                output.append("\n");
            }
            output.append(person.getPerson_address().get(i).getZip() + " " + person.getPerson_address().get(i).getCity().toUpperCase());
            output.append("\n");
            output.append(person.getPerson_address().get(i).getCountry().toUpperCase());
            output.append("\n");
            if (i < person.getPerson_address().size() - 1)
                output.append("\n");
        }
        if (output.toString().isEmpty()){
            output.append(createAlternativeLabel(person));
        }
        return output.toString();
    }

    private String createAlternativeLabel(Person person){
        CompletePerson completePerson = completeClassesServiceImp.getByPersonId(person.getPersonId());
        if (completePerson.getCompleteAddress().isEmpty())
            return person.getFirstName() + " " + person.getLastName() + "\nThis person has no contact information...\n";
        else {
            StringBuilder output = new StringBuilder();
            output.append(completePerson.getFirstName() + " " + completePerson.getLastName());
            output.append("\n\n");
            completePerson.getCompleteAddress()
                    .stream()
                    .forEach(e -> {
                output.append(e.getType() + ":\n");
                output.append(e.getEmail() + "\n");
                output.append(e.getMobile() + "\n");
                output.append(e.getPhone() + "\n");
            });
            return output.toString();
        }
    }

    private List<Person> getPersonsFromEvent(Event event){
        List<Integer> membersByPersonId = memberServiceImp.findAll()
                .stream()
                .filter(e -> e.getMemberId().getCommunity_id() == event.getCommunity().getCommunityId())
                .mapToInt(e -> e.getMemberId().getPerson_id())
                .boxed()
                .collect(Collectors.toList());
        return personServiceImp.findAll()
                .stream()
                .filter(e -> membersByPersonId.contains(e.getPersonId()))
                .collect(Collectors.toList());

    }

    @Autowired
    public void setPersonServiceImp(PersonServiceImp personServiceImp) {
        this.personServiceImp = personServiceImp;
    }

    @Autowired
    public void setMemberServiceImp(MemberServiceImp memberServiceImp) {
        this.memberServiceImp = memberServiceImp;
    }

    @Autowired
    public void setCompleteClassesServiceImp(CompleteClassesServiceImp completeClassesServiceImp) {
        this.completeClassesServiceImp = completeClassesServiceImp;
    }
}
