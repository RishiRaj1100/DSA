/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* partition(ListNode* head, int x) {
         ListNode* low = new ListNode(100);
        ListNode* high = new ListNode(234);
        ListNode* tempLo = low;
        ListNode* tempHi = high;
        ListNode* temp = head;

        while(temp!=NULL){
            if(temp->val < x){
                tempLo->next = temp;
                temp = temp->next;
                tempLo = tempLo->next;
            }
            else{
                tempHi->next = temp;
                temp = temp->next;
                tempHi = tempHi->next;
            }
        }
        tempLo->next = high->next;
        tempHi->next = NULL;
        return low->next;
    }
    };
