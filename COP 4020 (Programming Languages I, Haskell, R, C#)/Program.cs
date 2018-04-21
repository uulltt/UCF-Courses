using System;
//Aaron Varkonyi, COP 4020, Getting Started with C#

namespace GettingStartedWithCSharp
{
    public struct contactInfo
    {
        public string firstName, lastName;

        public string address, city, state;
        public override string ToString()
        {
            return firstName + " " + lastName + "; " + address + ", " + city + ", " + state;
        }
    }

    //the node for each of our contacts in our linked list
    class Contact
    {
        public contactInfo data;
        public Contact next;
        public Contact(contactInfo data, Contact next)
        {
            this.next = next;
            this.data = data;
            
        }
        public Contact(contactInfo data)
        {
            this.next = null;
            this.data = data; 
        }       
    }

    //the linked list actually containing our contacts
    public class contactList : System.Collections.Generic.IEnumerable<contactInfo>
    {
        private int length;
        private Contact start;
       
        public System.Collections.Generic.IEnumerator<contactInfo> GetEnumerator()
        {
            var c = start;
            while (c != null)
            {
                yield return c.data;
                c = c.next;
            }
        }

        System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        public contactList()
        {
            length = 0;
            start = null;
        }

        public int getLength()
        {
            return length;
        }

        public void addToEnd(contactInfo contact)
        {
            if (length == 0)
            {
                addToStart(contact);
            }
            else
            {
                Contact current = start;
                while (current.next != null)
                {
                    current = current.next;
                }
                length++;
                current.next = new Contact(contact);   
            }
        }

        public void addToStart(contactInfo contact)
        {
            length++;
            start = new Contact(contact, start);      
        }
    }

   
  
    class Program
    {
        public static contactList contacts;
        static void Main(string[] args)
        {
            contacts = new contactList();
            string str = "";
            do
            {
                askForContactInfo();
                Console.WriteLine("Type \"quit\" to finish entering in contacts. Otherwise, type anything else to continue.");
                str = Console.ReadLine();
            } while (str != "quit");
            Console.WriteLine("\nContacts:");
            displayContacts();
            Console.ReadKey();
        }

        public static void askForContactInfo()
        {
            contactInfo c;
            Console.WriteLine("Enter in your contact's first name:");
            c.firstName = Console.ReadLine();
            Console.WriteLine("Enter in your contact's last name:");
            c.lastName = Console.ReadLine();
            Console.WriteLine("Enter in your contact's address:");
            c.address = Console.ReadLine();
            Console.WriteLine("Enter in your contact's city:");
            c.city = Console.ReadLine();
            Console.WriteLine("Enter in your contact's state:");
            c.state = Console.ReadLine();
            contacts.addToEnd(c);
        }

        public static void displayContacts()
        {
            foreach(contactInfo c in contacts)
            {
                Console.WriteLine(c.ToString());
            }
        }
    }
}
