package com.learning.send;

/**
 * Abstraction over the WhatsApp Cloud API's send-message call. A real implementation would POST
 * to https://graph.facebook.com/v{version}/{phone-number-id}/messages with a bearer token and a
 * body shaped like:
 * <pre>
 * {
 *   "messaging_product": "whatsapp",
 *   "to": "&lt;recipient E.164 number&gt;",
 *   "type": "text",
 *   "text": { "body": "&lt;message&gt;" }
 * }
 * </pre>
 * That needs a Meta developer account, a verified WhatsApp Business number, and API credentials
 * this environment doesn't have - see SimulatedWhatsAppClient for what stands in for it here.
 */
public interface WhatsAppClient {
    SendResult sendMessage(String toNumber, String body);
}
