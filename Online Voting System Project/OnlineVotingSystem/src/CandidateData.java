import java.util.ArrayList;

public class CandidateData {
    private ArrayList<String[]> people;
    private ArrayList<String[]> furtherInfo;

    public CandidateData() {
        people = new ArrayList<>();
        furtherInfo = new ArrayList<>();

        // Initialize people and furtherInfo ArrayLists with default values
        people.add(new String[]{"Din", "FPM", "KK028"});
        people.add(new String[]{"Taufiq", "FSSM", "KK087"});
        people.add(new String[]{"MinDin", "FTKKI", "KK033"});
        people.add(new String[]{"Imranz", "FPSM", "KK015"});
        people.add(new String[]{"Ipat", "FPEPS", "KK010"});

        // Initialize further info of each candidate
        furtherInfo.add(new String[]{"Muhammad Farid Izzudin Bin Sulaiman", "Fakulti Pengajian Maritim.", "Second Year",
                                     "1.Menyelenggara kemudahan kolej."
                                   + "\n2.Menjadi suara Mahasiswa/Mahasiswi bagi menyampaikan isu & cadangan"});

        furtherInfo.add(new String[]{"Muhammad Taufiq Hakimi Bin Azhar", "Fakulti Sains & Sekitaran Marin", "First Year",
                                    "1.Mewujudkan kepimpinan MPP yang terbaik dan berwibawa."
                                  + "\n2.Memastikan persekitaran UMT sentisa terbaik di kalangan IPTA Malaysia."});

        furtherInfo.add(new String[]{"Mohd Amin Saifudin Bin Bakri", "Fakulti Teknologi Kejuruteraan Kelautan & Informatik.", "First Year",
                                    "1.Mewujudkan Barbershop di kolej kediaman."
                                  + "\n2.Menambah baik sistem penyampaian aduan penyelenggaraan & maklumat."});

        furtherInfo.add(new String[]{"Imran Bin Bahar", "Fakulti Perikanan & Sains Makanan.", "Third Year",
                                    "1.Memastikan program yang relevan untuk memenuhi keperluan pelajar."
                                  + "\n2.Menciptakan lingkungan yang inklusif dan berdaya guna bagi pelajar."});

        furtherInfo.add(new String[]{"Iffat Syahmi Bin Abdullah", "Fakulti Perniagaan, Ekonomi & Pembangunan Sosial.", "Third Year",
                                     "1.Menyediakan kemudahan bas mini untuk pelajar ke kompleks kuliah."
                                   + "\n2.Menambah baik persekitaran kelas supaya lebih kondusif bagi pelajar."});
    }
    // Define getter
    public ArrayList<String[]> getPeople() {
        return people;
    }

    public ArrayList<String[]> getFurtherInfo() {
        return furtherInfo;
    }

    // Method to add person and further info into arrayList...
    public void addPerson(String[] person) {
        people.add(person);
    }

    public void addFurtherInfo(String[] info) {
        furtherInfo.add(info);
    }

    // Method to retrieve the candidate's image file name
    public String getCandidateImageFileName(int index) {
        if (index >= 0 && index < people.size()) {
            String[] person = people.get(index);
            String[] imageFileNames = {
                    "C:\\CSF3034\\LoginGUI\\CandidateImages\\KK028.jpg",
                    "C:\\CSF3034\\LoginGUI\\CandidateImages\\KK087.jpg",
                    "C:\\CSF3034\\LoginGUI\\CandidateImages\\KK033.jpg",
                    "C:\\CSF3034\\LoginGUI\\CandidateImages\\KK015.jpg",
                    "C:\\CSF3034\\LoginGUI\\CandidateImages\\KK010.jpg"
            };

            if (index >= 0 && index < imageFileNames.length) {
                return imageFileNames[index];
            }
        }
        return null;
    }
}