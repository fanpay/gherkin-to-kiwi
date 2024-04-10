package org.opendcs.testing.kiwi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class TestCase
{
    private final long id;
    private final String summary;
    private final String steps;
    private final Priority priority;
    private final Product product;
    private final Category category;
    private final String requirements;
    private final String notes;
    private final String arguments;
    private final String referenceLink;
    private final String status;
    private final List<Attachment> attachments;
    private final List<String> tags;
    private final List<Component> components;
    private final Properties properties;


    private TestCase(long id, String summary, String steps, Priority priority,
                     Product product, Category category, String requirements,
                     String notes, String arguments, String referenceLink,
                     String status, List<Attachment> attachments, List<String> tags,
                     List<Component> components, Properties properties)
    {
        this.id = id;
        this.summary = summary;
        this.steps = steps;
        this.priority = priority;
        this.product = product;
        this.category = category;
        this.requirements = requirements;
        this.notes = notes;
        this.arguments = arguments;
        this.referenceLink = referenceLink;
        this.status = status;
        this.attachments = attachments;
        this.tags = tags;
        this.components = components;
        this.properties = properties;
    }

    public Builder newBuilder()
    {
        return new Builder(this);
    }

    public long getId()
    {
        return id;
    }

    public String getSummary()
    {
        return summary;
    }

    public String getSteps()
    {
        return steps;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public Product getProduct()
    {
        return product;
    }

    public Category getCategory()
    {
        return category;
    }

    public String getRequirements()
    {
        return requirements;
    }

    public String getNotes()
    {
        return notes;
    }

    public String getArguments()
    {
        return arguments;
    }

    public String getReferenceLink()
    {
        return referenceLink;
    }

    public String getStatus()
    {
        return status;
    }

    public List<Attachment> getAttachments()
    {
        return Collections.unmodifiableList(attachments);
    }

    public List<String> getTags()
    {
        return Collections.unmodifiableList(tags);
    }

    public List<Component> getComponents()
    {
        return Collections.unmodifiableList(components);
    }

    /**
     *
     * @return A copy of the properties object.
     */
    public Properties getProperties()
    {
        return new Properties(properties);
    }

    public String getProperty(String value)
    {
        return properties.getProperty(value);
    }

    @Override
    public String toString() {
        return "testcase{"
             + "Summary=" + this.summary + ","
             + "Product=" + this.product.name + ","
             + "Category=" + this.category.name + ","
             + "Components=" + this.components.toString() + ","
             + "}";
    }

    public static class Builder
    {
        private long id = -1;
        private List<Attachment> attachments = new ArrayList<>();
        private List<String> tags = new ArrayList<>();
        private List<Component> components = new ArrayList<>();
        private String summary = null;
        private String steps = null;
        private Priority priority = null;
        private Product product = null;
        private Category category = null;
        private String requirements = null;
        private String notes = null;
        private String arguments = null;
        private String referenceLink = null;
        private String status = "CONFIRMED";
        private Properties properties = new Properties();

        public Builder(String productName)
        {
            this.product = Product.of(productName);
            this.category = Category.of(product, "--default--");
        }

        public Builder(TestCase tc)
        {
            this.id = tc.id;
            this.summary = tc.summary;
            this.steps = tc.steps;
            this.priority = tc.priority;
            this.product = tc.product;
            this.category = tc.category;
            this.requirements = tc.requirements;
            this.notes = tc.notes;
            this.arguments = tc.arguments;
            this.referenceLink = tc.referenceLink;
            this.status = tc. status;
            this.attachments = new ArrayList<>(attachments);
            this.tags = new ArrayList<>(tags);
            this.components = new ArrayList<>(components);
            this.properties.putAll(tc.properties);
        }

        public TestCase build()
        {
            return new TestCase(id, summary, steps, priority, product,
                                category, requirements,notes,arguments,referenceLink,status,
                                attachments, tags, components, properties);
        }

        public Builder withId(long id)
        {
            this.id = id;
            return this;
        }

        public Builder withComponent(String component)
        {
            this.components.add(Component.of(product, component));
            return this;
        }

        public Builder withSummary(String summary)
        {
            this.summary = summary;
            return this;
        }

        public Builder withSteps(String steps)
        {
            this.steps = steps;
            return this;
        }

        public Builder withTag(String tag)
        {
            this.tags.add(tag);
            return this;
        }

        public Builder withNotes(String notes)
        {
            this.notes = notes;
            return this;
        }

        public Builder withRequirements(String requirements)
        {
            this.requirements = requirements;
            return this;
        }

        public Builder withCategory(String category, String productName)
        {
            this.product = Product.of(productName);
            this.category = Category.of(product, category);

            return this;
        }

        public Builder withCategory(String category)
        {
            this.category = Category.of(this.product, category);
            return this;
        }

        public Builder withPriority(String priority)
        {
            this.priority = Priority.of(priority);
            return this;
        }

        public Builder withStatus(String status)
        {
            this.status = status;
            return this;
        }

        public Builder withReferenceLink(String link)
        {
            this.referenceLink = link;
            return this;
        }

        /**
         * Property to set or unset
         * @param name name of the property
         * @param value value for the property. If null property will be removed.
         * @return
         */
        public Builder withProperty(String name, String value)
        {
            if (value != null)
            {
                this.properties.setProperty(name, value);
            }
            else
            {
                this.properties.remove(name);
            }
            return this;
        }
    }

    public static class TestCaseProperty
    {
        public final long id;
        public final long caseId;
        public final String name;
        public final String value;

        public TestCaseProperty(long id, long caseId, String name, String value)
        {
            this.id = id;
            this.caseId = caseId;
            this.name = name;
            this.value = value;
        }
    }
}
